package com.zzx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 */
@SpringBootApplication
@Slf4j
public class StreamConsumerMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain8003.class,args);
        log.info("********** StreamConsumerMain8003 启动成功 ********");
    }
}