package com.zzx.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装返回信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Response {
    private Integer code;
    private String message;
}
