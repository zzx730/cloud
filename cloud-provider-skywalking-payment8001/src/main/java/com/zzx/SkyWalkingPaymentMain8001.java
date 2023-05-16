package com.zzx;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 */
@SpringBootApplication
@Slf4j
public class SkyWalkingPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(SkyWalkingPaymentMain8001.class,args);
        log.info("****** SkyWalkingPaymentMain8001 服务启动成功 *****");
    }
}
