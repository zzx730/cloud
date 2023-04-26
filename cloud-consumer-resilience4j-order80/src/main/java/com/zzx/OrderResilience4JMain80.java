package com.zzx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主启动类
 */
@EnableFeignClients
@SpringBootApplication
@Slf4j
public class OrderResilience4JMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderResilience4JMain80.class,args);
        log.info("***** OrderResilience4JMain80服务 启动成功 *****");
    }
}
