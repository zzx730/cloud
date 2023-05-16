package com.zzx.service.impl;

import com.zzx.common.MyMessage;
import com.zzx.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * 定义消息推送的管道
 */
@Service
public class MessageProviderImpl implements IMessageProvider {
    @Autowired
    private StreamBridge streamBridge;
    @Override
    public String send(String message) {
        MyMessage myMessage = new MyMessage();
        myMessage.setPayload(message);
        /**
         * 第一个参数为 生产者绑定的名称
         * 第二个参数为 发送的消息实体
         */
        streamBridge.send("myBroadcast-out-0",myMessage);
        return "success";
    }

    @Override
    public String groupSend(String message) {
        MyMessage myMessage = new MyMessage();
        myMessage.setPayload(message);
        /**
         * 第一个参数为 生产者绑定的名称
         * 第二个参数为 发送的消息实体
         */
        streamBridge.send("myGroup-out-0",myMessage);
        return "success";
    }
}
