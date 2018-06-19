package org.apel.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wangbowen
 * @Description 服务注册中心启动类
 * @Date 2018/6/19 15:52
 */
@EnableEurekaServer
@SpringBootApplication
public class OpenCenter {
    public static void main(String[] args) {
        SpringApplication.run(OpenCenter.class,args);
        System.out.println("------服务注册中心启动成功--------");
    }
}
