package org.apel.open.message.provider;

import org.apel.opem.message.constant.MessageConstant;
import org.apel.open.common.exception.ServiceException;
import org.apel.open.common.sequence.IdWorker;
import org.apel.open.message.activemq.MessageSender;
import org.apel.open.message.exception.MessageExceptionEnum;
import org.apel.open.message.service.MessageService;
import org.apel.open.repository.MessageInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apel.opem.message.domain.MessageInfo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangbowen
 * @Description 消息服务提供接口的实现
 * @Date 2018/6/20 10:29
 */
@RestController
public class MessageProvider  implements MessageService {

    @Autowired
    private MessageInfoRepository messageInfoRepository;

    @Autowired
    private MessageSender messageSender;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageInfo preSaveMessage(@RequestBody  MessageInfo message) {

        //检查数据的完整性
        this.checkEmptyMessage(message);

        message.setId(IdWorker.getId());

        message.setMessageId(IdWorker.getId());

        //设置消息的状态为待确认状态
        message.setStatus(MessageConstant.Status.WAIT_VERIFY);

        //标记消息未死亡
        message.setAlreadyDead(MessageConstant.AlreadyDead.N);

        //设置消息重发次数
        message.setMessageSendTimes(0L);

        message.setUpdateTime(new Date());

        //保存
        MessageInfo messageinfo =  messageInfoRepository.save(message);

        return messageinfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmAndSendMessage(@PathVariable("messageId") Long messageId) {

        if(Objects.isNull(messageId)){
            throw  new  RuntimeException("消息ID为空");
        }

        //查询消息是否存在
        MessageInfo byMessage = messageInfoRepository.findByMessageId(messageId);

        if(Objects.isNull(byMessage)){

            throw  new  RuntimeException(MessageExceptionEnum.CANT_FIND_MESSAGE.name());
        }

        //设置消息的状态为发送中
        byMessage.setStatus(MessageConstant.Status.SENDING);

        byMessage.setUpdateTime(new Date());

        messageInfoRepository.modify(byMessage);

        //发送消息
        messageSender.sendMessage(byMessage);
    }

    @Override
    public void send(@RequestBody MessageInfo message) {
        //检查消息数据的完整性
        this.checkEmptyMessage(message);

        message.setStatus(MessageConstant.Status.SENDING);

        message.setAlreadyDead(MessageConstant.AlreadyDead.N);

        message.setMessageSendTimes(0L);

        message.setUpdateTime(new Date());

        messageInfoRepository.save(message);

        //发送消息
        messageSender.sendMessage(message);
    }
    @Override
    public void directSendMessage(@RequestBody  MessageInfo message) {
        //检查消息数据的完整性
        this.checkEmptyMessage(message);

        messageSender.sendMessage(message);
    }
    @Override
    public void reSendMessageByMessageId(@PathVariable  Long messageId) {
        //检查消息数据的完整性
       if(Objects.isNull(messageId)){
           throw  new RuntimeException("消息ID不能为空");
       }

       //查询消息
        MessageInfo messageInfo = messageInfoRepository.findByMessageId(messageId);

       if(Objects.nonNull(messageInfo)){
           //更新消息发送次数
           Long messageSendTimes = messageInfo.getMessageSendTimes()+1;

           messageInfo.setMessageSendTimes(messageSendTimes);

           messageInfo.setUpdateTime(new Date());

           messageInfoRepository.save(messageInfo);

           //发送消息
           messageSender.sendMessage(messageInfo);
       }
    }
    @Override
    public void reSendMessage(@RequestBody  MessageInfo message) {

        //检查消息数据的完整性
        this.checkEmptyMessage(message);

        //更新消息发送次数
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);

        message.setUpdateTime(new Date());

        messageInfoRepository.modify(message);

        //发送消息
        messageSender.sendMessage(message);
    }

    @Override
    public void reSendMessageByQueue(@PathVariable  String queue) {
        if(Objects.isNull(queue)){
            throw  new RuntimeException("消息队列名称不能为空");
        }
        //默认分页大小为1000
        Integer pageSize = 1000;

        //查询已死亡的消息
        Integer total = messageInfoRepository.findByConsumerQueue(queue);

        if(!Objects.equals(0,total)){

            Integer pages = Objects.equals(total % pageSize, 0) ? total / pageSize : total / pageSize + 1;

            for (int i = 1; i <= pages; i++) {
                List<MessageInfo> messages = messageInfoRepository.findByQueue(queue, (i - 1) * pageSize, pageSize);

                for (MessageInfo message : messages) {

                    message.setMessageSendTimes(message.getMessageSendTimes() + 1);

                    message.setUpdateTime(new Date());

                    messageInfoRepository.modify(message);

                    messageSender.sendMessage(message);
                }
            }
        }


    }

    @Override
    public void setMessageToDied(@PathVariable  Long messageId) {
        //检查消息数据的完整性
        if(Objects.isNull(messageId)){
            throw  new RuntimeException("消息ID不能为空");
        }

        MessageInfo messageInfo = messageInfoRepository.findByMessageId(messageId);

        if(Objects.isNull(messageInfo)){
            throw  new RuntimeException("消息为空");
        }

        messageInfo.setAlreadyDead(MessageConstant.AlreadyDead.Y);

        messageInfo.setUpdateTime(new Date());

        messageInfoRepository.modify(messageInfo);

        messageSender.sendMessage(messageInfo);

    }

    @Override
    public void removeByMessageId(@PathVariable Long messageId) {
        if(Objects.isNull(messageId)){
            throw  new RuntimeException("消息ID不能为空");
        }
        messageInfoRepository.deleteByMessageId(messageId);
    }

    @Override
    public void removeByBizId(@PathVariable Long bizId) {
        if(Objects.isNull(bizId)){
            throw  new RuntimeException("消息ID不能为空");
        }
        messageInfoRepository.deleteByBizId(bizId);
    }

    @Override
    public MessageInfo findByMessageId(@PathVariable  Long messageId) {
        if(Objects.isNull(messageId)){
            throw  new RuntimeException("消息ID不能为空");
        }
        return messageInfoRepository.findByMessageId(messageId);
    }

    @Override
    public List<MessageInfo> findConfirmTimeOutMessages(@RequestParam  Map<String, Object> params) {
        return null;
    }

    @Override
    public List<MessageInfo> findSendingTimeOutMessages(@RequestParam Map<String, Object> params) {
        return null;
    }






    /**
     * 检查数据的完整性
     * @param messageInfo
     */
    private void checkEmptyMessage(MessageInfo messageInfo) {
        if (Objects.isNull(messageInfo)) {
            throw new ServiceException(MessageExceptionEnum.REQUEST_NULL);
        }
        if (Objects.isNull(messageInfo.getMessageId())) {
            throw new ServiceException(MessageExceptionEnum.MESSAGE_ID_CANT_EMPTY);
        }
        if (Objects.isNull(messageInfo.getMessageBody())) {
            throw new ServiceException(MessageExceptionEnum.MESSAGE_BODY_CANT_EMPTY);
        }
        if (Objects.isNull(messageInfo.getConsumerQueue())) {
            throw new ServiceException(MessageExceptionEnum.QUEUE_CANT_EMPTY);
        }
    }
}
