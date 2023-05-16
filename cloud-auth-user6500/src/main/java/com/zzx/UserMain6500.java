package com.zzx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 */
@Slf4j
@SpringBootApplication
public class UserMain6500 {
    public static void main(String[] args) {
        SpringApplication.run(UserMain6500.class,args);
        log.info("************ UserMain6500服务 启动成功 ************");
    }
}