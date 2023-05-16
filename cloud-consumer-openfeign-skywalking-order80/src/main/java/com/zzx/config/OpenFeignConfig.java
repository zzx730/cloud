package com.zzx.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {
    /**
     * 日志级别定义
     */
   @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
