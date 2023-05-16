package com.zzx.service;

import org.springframework.stereotype.Service;

/**
 * 发送消息的接口
 */
public interface IMessageProvider {
    /**
     * 发送消息
     * @param message 消息的内容
     * @return
     */
    String send(String message);
    String groupSend(String message);
}
