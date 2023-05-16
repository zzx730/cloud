package com.zzx.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 日志网关过滤器
 */
@Component
@Slf4j
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 表示配置填写顺序
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog");
    }

    /**
     * 执行过滤的逻辑
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(config.consoleLog){
                log.info("********* consoleLog日志 开启 ********");
            }
            return chain.filter(exchange);
        });
    }

    /**
     * 过滤器使用的配置内容
     *
     */
    @Data
    public static class Config{
        private boolean consoleLog;
    }

}
