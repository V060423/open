package org.apel.open.message.activemq.impl;

import org.apel.opem.message.domain.MessageInfo;
import org.apel.open.message.activemq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wangbowen
 * @Description 消息队列发送者
 * @Date 2018/6/20 10:42
 */
@Service
public class MessageSenderImpl implements MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(MessageInfo message) {

        if(Objects.nonNull(message)){
            throw  new RuntimeException("消息队列为null");
        }

        //设置队列名称
        jmsTemplate.setDefaultDestinationName(message.getConsumerQueue());

        //设置ack确认为client方式
        jmsTemplate.setSessionAcknowledgeMode(JmsProperties.AcknowledgeMode.CLIENT.getMode());

        //发送消息
        jmsTemplate.send(session -> session.createTextMessage(message.getMessageBody()));
    }
}
