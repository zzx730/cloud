package com.zzx.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用户鉴权全局过滤器
 */
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    /**
     * 自定义全局过滤器逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1。请求中获取Token令牌
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //2.判断token是否为空
        if(StringUtils.isEmpty(token)){
            System.out.println("鉴权失败，令牌为空");
            //将状态码设置为未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //3。判断token是否有效
        if(!token.equals("zzx")){
            System.out.println("token令牌无效");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 全局过滤器执行顺序排序 数值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
