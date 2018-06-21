package org.apel.open.listener;

import org.apel.open.constant.LogConstants;
import org.apel.open.model.CommonLog;
import org.apel.open.service.CommonLogService;
import org.apel.open.service.TraceLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wangbowen
 * @Description 日志消费者监听器
 * @Date 2018/6/19 15:19
 */
@Service
public class LoggerConsumerListener {

    @Autowired
    private TraceLogService traceLogService;


    @Autowired
    private CommonLogService commonLogService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    private final static Logger logger = LoggerFactory.getLogger(LoggerConsumerListener.class);

    /**
     * 接收队列
     */
    @Scheduled(cron="0/5 * * * * *")
    public void receiveQueue(){
        BoundListOperations<String, Object> options = redisTemplate.boundListOps(LogConstants.COMMON_LOG);

        CommonLog log = (CommonLog) options.rightPop();

        if(Objects.nonNull(log) &&Objects.nonNull(log.getCreateTime())){

        }
    }

}
