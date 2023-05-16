package com.zzx.service;

import com.zzx.common.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
 * 消费者类
 */
public class Consumer {
    /**
     * 消费广播消息
     * @return
     */
    @Bean
    public java.util.function.Consumer<MyMessage> myBroadcast(){
        return myMessage -> {
            log.info("接收到了广播消息：{}",myMessage.getPayload());
        };
    }
    @Bean
    public java.util.function.Consumer<MyMessage> myGroup(){
        return myMessage -> {
            log.info("接收到了分组消息：{}",myMessage.getPayload());
        };
    }
}
