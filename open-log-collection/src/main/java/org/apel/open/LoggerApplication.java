package org.apel.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangbowen
 * @Description 日志服务，监听消息队列中的日志,并存储到数据库
 * @Date 2018/6/19 15:05
 */
@SpringBootApplication
public class LoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
    }
}
