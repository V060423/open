package org.apel.open.message.checker.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author wangbowen
 * @Description message配置文件
 * @Date 2018/6/20 9:23
 */
@Data
@ConfigurationProperties(prefix = "message")
public class MessageProperties {

    /**
     * 最大重试次数
     */
    private Integer  maxRetry;

    /**
     *最大处理分页数
     */
    private Integer  handleMax;

    /**
     * 最大处理个数
     */
    private Integer handleLimit;

    /**
     * 过期时间
     */
    private Long handleTimeoutMillis;


    /**
     * 重试时间段
     */
    private List<Long> retryDelayedMillis;
}
