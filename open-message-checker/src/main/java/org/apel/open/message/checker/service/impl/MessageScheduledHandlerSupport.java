package org.apel.open.message.checker.service.impl;

import org.apel.opem.message.domain.MessageInfo;
import org.apel.open.message.checker.config.MessageProperties;
import org.apel.open.message.checker.service.MessageScheduledHandler;
import org.apel.open.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author wangbowen
 * @Description 消息校验重发服务实现接口
 * @Date 2018/6/20 13:51
 */
@Service
@EnableConfigurationProperties({MessageProperties.class})
public class MessageScheduledHandlerSupport implements MessageScheduledHandler,ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;

    @Autowired
    private MessageProperties messageProperties;

    @Autowired
    private MessageService messageService;

    @Override
    public void handleSendingTimeoutMessages() {

        Date timeout = new Date(System.currentTimeMillis()-messageProperties.getHandleTimeoutMillis());

        Map<String,Object> map = new HashMap<>();

        map.put("timeout",timeout);

        Integer total = messageService.findTotalSendingTimeoutMessages(map);

        if (!Objects.equals(0, total)) {

            //最大处理分页数
            Integer max = messageProperties.getHandleMax();

            //最大处理个数
            Integer limit = messageProperties.getHandleLimit();

            Integer pages = Objects.equals(total % limit, 0) ? total / limit : total / limit + 1;

            max = pages < max ? pages : max;
            for (int i = 1; i <= max; i++) {

                Map<String,Object> condition = new HashMap<>();

                condition.put("pageSize",(i-1));

                condition.put("limit",limit);

                condition.put("timeout",timeout);

                List<MessageInfo> messages = messageService.findSendingTimeOutMessages(condition);

                for (MessageInfo message : messages) {
                    Integer retry = message.getMessageSendTimes();

                    Integer maxRetry = messageProperties.getMaxRetry() - 1;

                    Long messageId = message.getMessageId();

                    //判断如果重试次数大于最大重试次数，直接标记消息为“已死亡消息”
                    if (retry > maxRetry) {
                        messageService.setMessageToDied(messageId);
                        continue;
                    }
                    List<Long> retryDelayedMillis = messageProperties.getRetryDelayedMillis();

                    Long delayed = retryDelayedMillis.get(retry);

                    boolean isDelayed = (System.currentTimeMillis() - delayed - message.getUpdateTime().getTime()) > 0;

                    if (isDelayed){
                        messageService.reSendMessageByMessageId(messageId);
                    }
                }
                }
            }
    }

    @Override
    public void handleConfirmingTimeoutMessages() {
        {

            Date timeout = new Date(System.currentTimeMillis()-messageProperties.getHandleTimeoutMillis());

            Map<String,Object> map = new HashMap<>();

            map.put("timeout",timeout);

            Integer total = messageService.findTotalSendingTimeoutMessages(map);

            if (!Objects.equals(0, total)) {

                //最大处理分页数
                Integer max = messageProperties.getHandleMax();

                //最大处理个数
                Integer limit = messageProperties.getHandleLimit();

                Integer pages = Objects.equals(total % limit, 0) ? total / limit : total / limit + 1;

                max = pages < max ? pages : max;
                for (int i = 1; i <= max; i++) {

                    Map<String,Object> condition = new HashMap<>();

                    condition.put("pageSize",(i-1));

                    condition.put("limit",limit);

                    condition.put("timeout",timeout);

                    List<MessageInfo> messages = messageService.findSendingTimeOutMessages(condition);

                    for (MessageInfo message : messages){

                        try {
                            String field1 = message.getField1();//类完全限定名

                            String field2 = message.getField2();//方法名

                            String field3 = message.getBizId();//业务id

                            Long messageId = message.getMessageId();

                            if (logger.isDebugEnabled()) {
                                logger.info("class : {} - method : {} - params : {}", field1, field2, field3);
                            }


                            Class<?> clz = Class.forName(field1);

                            Method method = clz.getDeclaredMethod(field2, String.class);

                            Object service = applicationContext.getBean(clz);

                            Object handled = method.invoke(service, field3);

                            if (handled instanceof Boolean) {
                                if ((boolean) handled) {
                                    messageService.confirmAndSendMessage(messageId);
                                } else {
                                    messageService.removeByMessageId(messageId);
                                }
                            }

                            if (logger.isDebugEnabled()) {
                                logger.error("return type is not boolean , message id is {}", message.getId());
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            if (logger.isDebugEnabled()) {
                                logger.error("{}", e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
