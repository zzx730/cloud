package com.zzx.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 将RestTemplate放入到Spring的IOC容器中
 */
@Configuration

public class RestTemplateConfig {
    //给这个组件 有负载均衡的功能
    //@LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
