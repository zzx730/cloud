package com.zzx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${config.info}")
    private String configInfo;

    /**
     * 读取配置文件的内容
     * @return
     */
    @GetMapping("getConfigInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
