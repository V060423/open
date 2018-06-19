package org.apel.open.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.swing.text.html.Option;

/**
 * @author wangbowen
 * @Description 服务配置中心启动类
 * @Date 2018/6/19 16:03
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class OpenConfig {

    public static void main(String[] args) {
        SpringApplication.run(Option.class,args);
        System.out.println("---服务配置中心启动成功---");
    }
}
