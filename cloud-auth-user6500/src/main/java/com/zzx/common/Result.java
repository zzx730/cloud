package com.zzx.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回实体类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {
    // 状态码
    private int code;
    // 描述信息
    private String msg;
    // token令牌
    private String token;
}
