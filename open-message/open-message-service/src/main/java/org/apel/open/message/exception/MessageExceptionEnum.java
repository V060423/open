package org.apel.open.message.exception;


import org.apel.open.common.exception.AbstractServiceException;

/**
 * @author wangbowen
 * @Description 消息异常集合
 * @Date 2018/6/20 10:15
 */
public enum MessageExceptionEnum implements AbstractServiceException {


    REQUEST_NULL(500,"消息对象不能为空"),
    QUEUE_CANT_EMPTY(600, "消息队列不能为空"),
    MESSAGE_ID_CANT_EMPTY(601, "消息id不能为空"),
    MESSAGE_BODY_CANT_EMPTY(602, "消息body不能为空"),
    CANT_FIND_MESSAGE(603, "查找不到消息"),
    MESSAGE_NUMBER_WRONG(604, "消息数量错误"),
    MESSAGE_QUEUE_ERROR(605,"消息队列服务器处理异常"),
    MESSAGE_TYPE_ERROR(606,"消息接收到的格式错误，非TEXT类型");

    MessageExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;


    @Override
    public Integer getCode() {
        return  code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
