package com.zzx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AlarmMain9090 {
    public static void main(String[] args) {
        SpringApplication.run(AlarmMain9090.class,args);
        log.info("******  AlarmMain9090 启动 *****");
    }
}