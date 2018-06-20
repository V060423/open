package org.apel.open.message.activemq;

import org.apel.opem.message.domain.MessageInfo;

/**
 * @author wangbowen
 * @Description 消息发送器
 * @Date 2018/6/20 10:39
 */
public interface MessageSender {
    /**
     * 发送消息
     * @param message
     */
    void sendMessage(MessageInfo message);
}
