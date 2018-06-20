package org.apel.open.message.fallback;

import org.apel.opem.message.domain.MessageInfo;
import org.apel.open.message.service.MessageService;

import java.util.List;
import java.util.Map;

/**
 * @author wangbowen
 * @Description 消息服务回调接口
 * @Date 2018/6/20 9:31
 */
public class MessageFallback  implements MessageService {
    @Override
    public MessageInfo preSaveMessage(MessageInfo message) {
      throw  new RuntimeException();
    }

    @Override
    public void confirmAndSendMessage(Long messageId) {
        throw  new RuntimeException();
    }

    @Override
    public void send(MessageInfo message) {
        throw  new RuntimeException();
    }

    @Override
    public void directSendMessage(MessageInfo message) {
        throw  new RuntimeException();
    }

    @Override
    public void reSendMessageByMessageId(Long messageId) {
        throw  new RuntimeException();
    }

    @Override
    public void reSendMessage(MessageInfo message) {
        throw  new RuntimeException();
    }

    @Override
    public void reSendMessageByQueue(String queue) {
        throw  new RuntimeException();
    }

    @Override
    public void setMessageToDied(Long messageId) {
        throw  new RuntimeException();
    }

    @Override
    public void removeByMessageId(Long messageId) {
        throw  new RuntimeException();
    }

    @Override
    public void removeByBizId(Long bizId) {
        throw  new RuntimeException();
    }

    @Override
    public MessageInfo findByMessageId(Long messageId) {
        throw  new RuntimeException();
    }

    @Override
    public List<MessageInfo> findConfirmTimeOutMessages(Map<String, Object> params) {
        throw  new RuntimeException();
    }

    @Override
    public List<MessageInfo> findSendingTimeOutMessages(Map<String, Object> params) {
        throw  new RuntimeException();
    }
}
