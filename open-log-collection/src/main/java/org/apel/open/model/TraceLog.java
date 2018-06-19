package org.apel.open.model;

import lombok.Data;

import java.util.Date;

/**
 * @author wangbowen
 * @Description 服务调用日志
 * @Date 2018/6/19 14:17
 */
@Data
public class TraceLog {

    /**
     * id
     */
    private Long  id;

    /**
     * 唯一请求号
     */
    private Long traceId;

    /**
     * 节点id
     */
    private Long spanId;

    /**
     * 节点父id
     */
    private Long  parentSpanId;

    /**
     * IP
     */
    private String ip;

    /**
     * 日志内容
     */
    private  String content;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 方法名
     */
    private String  methodName;

    /**
     * 请求路径
     */
    private String  requestPath;

    /**
     * RPC调用方式 网关发送请求、接收网关请求、调用端发送请求、被调用端接收到请求
     */
    private String rpcPhase;

    /**
     * 创建时间
     */
    private Date createTime;





}
