package org.apel.open.model;


import lombok.Data;

import java.util.Date;

/**
 * @author wangbowen
 * @Description 请求数据
 * @Date 2018/6/19 14:08
 */
@Data
public class RequestDataLog {

    /**
     * id
     */
    private Long id;


    /**
     * 应用id
     */
    private Long  appId;



    /**
     * 应用IP
     */
    private String  ip;



    /**
     * 应用名称
     */
    private String  applicationName;

    /**
     * 类名
     */
    private String  className;

    /**
     * 请求地址
     */
    private String  URL;


    /**
     * 账号id
     */
    private String  accountId;

    /**
     * 日志号
     */
    private String LogNum;

    /**
     * 日志类别
     */
    private String logCategory;

    /**
     * 日志简要内容
     */
    private String logContent;

    /**
     * 日志详情
     */
    private String logDetails;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 是否删除 Y:已删除   N:未删除
     */
    private String delFlag;





}
