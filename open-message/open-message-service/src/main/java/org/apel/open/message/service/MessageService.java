package org.apel.open.message.service;

import org.apel.opem.message.domain.MessageInfo;
import org.apel.open.message.fallback.MessageFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wangbowen
 * @Description 消息服务接口
 * @Date 2018/6/20 9:21
 */
@FeignClient(name = "component-message", fallback = MessageFallback.class)
public interface MessageService {


    /**
     *预存储消息
     * @param message 消息对象
     */
    @PostMapping("/messages/prepare")
    MessageInfo preSaveMessage(@RequestBody MessageInfo message);


    /**
     * 确认并发送消息
     * @param messageId 消息id
     */
    @PatchMapping("/messages//confirm/{messageId}")
    void  confirmAndSendMessage(@PathVariable("messageId") Long  messageId);


    /**
     * 存储并发送消息
     * @param message
     */
    @PostMapping("/messages/send")
    void send(@RequestBody MessageInfo message);


    /**
     * 直接发送消息
     * @param message
     */
    @PostMapping("/messages/direct")
    void directSendMessage(@RequestBody MessageInfo message);


    /**
     * 根据消息id重发消息
     * @param messageId 消息id
     */
    @PatchMapping("/messages/resend/{messageId}")
    void reSendMessageByMessageId(@PathVariable("messageId") Long messageId);


    /**
     * 重发消息
     * @param message
     */
    @PatchMapping("/messages/resend")
    void reSendMessage(@RequestBody MessageInfo message);

    /**
     * 根据队列名称重发消息
     * @param queue
     */
    @PatchMapping("/messages/resend/{queue}")
    void reSendMessageByQueue(@PathVariable("queue") String  queue);

    /**
     * 根据消息id标记消息为死亡消息
     * @param messageId
     */
    @PatchMapping("/messages/died/{messageId}")
    void setMessageToDied(@PathVariable("messageId") Long messageId);

    /**
     * 根据消息id删除消息
     * @param messageId  消息id
     */
    @DeleteMapping("/messages/{id}")
    void removeByMessageId(@PathVariable("messageId") Long  messageId);

    /**
     * 根据业务id删除消息
     * @param bizId  消业务id
     */
    @DeleteMapping("/messages/{bizId}")
    void removeByBizId(@PathVariable("bizId") Long  bizId);

    /**
     * 根据消息id查询消息
     * @param messageId 消息id
     * @return MessageInfo
     */
    @GetMapping("/messages/{messageId}")
    MessageInfo findByMessageId(@PathVariable("messageId") Long messageId);

    /**
     * 分页获取待发送超时的数据
     * @param params
     * @return
     */
    @GetMapping("/messages/confirm/timeout")
    List<MessageInfo> findConfirmTimeOutMessages(@RequestParam Map<String, Object> params);
    /**
     * 分页获取发送中超时的数据
     * @param params
     * @return
     */
    @GetMapping("/messages/confirm/timeout")
    List<MessageInfo> findSendingTimeOutMessages(@RequestParam Map<String, Object> params);

    /**
     * 查询状态为“发送中” 但是已经超时的消息数量
     * @param params
     */
    @GetMapping("/messages/sending/timeout/count")
    Integer findTotalSendingTimeoutMessages(@RequestParam Map<String, Object> params);

    /**
     * 查询状态为“待确认” 但是已经超时的消息数量
     * @param params
     */
    @GetMapping("/messages/confirm/timeout/count")
    Integer findTotalConfirmTimeoutMessages(@RequestParam Map<String, Object> params);
}
