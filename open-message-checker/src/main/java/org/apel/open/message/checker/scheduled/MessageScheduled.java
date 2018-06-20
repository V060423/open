package org.apel.open.message.checker.scheduled;

import org.apel.open.message.checker.service.MessageScheduledHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author wangbowen
 * @Description 消息定时执行器
 * @Date 2018/6/20 14:23
 */
@Configuration
@EnableScheduling
public class MessageScheduled {

    @Autowired
    private MessageScheduledHandler handler;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedDelay = 60 * 1000)
    public void sendingTimeoutMessagesScheduled() {
        if (logger.isDebugEnabled()) {
            logger.info("{}", "handling sending timeout messages");
        }

        handler.handleSendingTimeoutMessages();

        if (logger.isDebugEnabled()) {
            logger.info("{}", "handled sending timeout messages");
        }
    }

    @Scheduled(fixedDelay = 90 * 1000)
    public void confirmingTimeoutMessagesScheduled() {
        if (logger.isDebugEnabled()) {
            logger.info("{}", "handling confirming timeout messages");
        }

        handler.handleConfirmingTimeoutMessages();

        if (logger.isDebugEnabled()) {
            logger.info("{}", "handled confirming timeout messages");
        }
    }
}
