package org.apel.open.listener;

import org.apel.open.service.CommonLogService;
import org.apel.open.service.TraceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
