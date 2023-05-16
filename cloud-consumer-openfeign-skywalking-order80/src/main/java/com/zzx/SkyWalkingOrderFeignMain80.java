package com.zzx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
//开启openfeign服务调用
@EnableFeignClients
public class SkyWalkingOrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(SkyWalkingOrderFeignMain80.class,args);
        log.info("***** SkyWalkingOrderFeignMain80 启动成功 ******");
    }
}
