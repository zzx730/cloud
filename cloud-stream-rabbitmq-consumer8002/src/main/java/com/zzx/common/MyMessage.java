package com.zzx.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyMessage implements Serializable {
    /**
     * 消息体
     */
    private String payload;
}
