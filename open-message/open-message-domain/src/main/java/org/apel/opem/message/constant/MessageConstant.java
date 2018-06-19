package org.apel.opem.message.constant;

/**
 * @author wangbowen
 * @Description 消息接口常量
 * @Date 2018/6/19 17:02
 */
public interface MessageConstant {

    interface  Status{
        /**
         * 发送中
         */
        String SENDING ="SENDING";

        /**
         * 待确认
         */
        String WAIT_VERIFY ="WAIT_VERIFY";
    }
}
