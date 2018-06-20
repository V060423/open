package org.apel.opem.message.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author wangbowen
 * @Description 可靠消息实体对象
 * @Date 2018/6/19 16:53
 */
@Data
public class MessageInfo {


    /**
     * id
     */
    private  Long  id;

    /**
     * 消息id
     */
    private  Long  messageId;

    /**
     * 消息内容
     */
    private  String messageBody;

    /**
     * 消息数据类型
     */
    private String  messageDataType;

    /**
     * 消息队列
     */
    private String consumerQueue;

    /**
     * 消息重试次数
     */
    private Integer messageSendTimes;


    /**
     * 消息是否死亡 Y：已死亡 N：未死亡
     */
    private String alreadyDead;


    /**
     * 消息状态 WAIT_VERIFY：待确认  SENDING：发送中
     */
    private  String status;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String  createBy;


    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String  updateBy;


    /**
     * 备注
     */
    private String remark;
    /**
     * 版本
     */
    private  String version;


    /**
     * 业务id
     */
    private String  bizId;

    /**
     * 扩展字段1
     */
    private String field1;
    /**
     * 扩展字段2
     */
    private String field2;
    /**
     * 扩展字段3
     */
    private String field3;


}
