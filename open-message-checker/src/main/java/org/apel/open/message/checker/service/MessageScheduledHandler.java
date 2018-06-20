package org.apel.open.message.checker.service;
/**
 * @author wangbowen
 * @Description  消息校验重发服务
 * @Date 2018/6/20 9:23
 */
public interface MessageScheduledHandler {
    /**
     * 处理状态为 “发送中” 但已经超时的消息
     */
    void handleSendingTimeoutMessages();

    /**
     * 处理状态为 “待确认” 但已经超时的消息
     */
    void handleConfirmingTimeoutMessages();
}
