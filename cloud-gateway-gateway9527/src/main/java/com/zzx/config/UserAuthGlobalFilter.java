package com.zzx.config;

import com.alibaba.fastjson.JSONObject;
import com.zzx.common.Response;
import com.zzx.utils.JWTUtils;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 用户鉴权全局过滤器
 */
@Data
@ConfigurationProperties("org.my.jwt")
@Component
@Slf4j
public class UserAuthGlobalFilter implements GlobalFilter, Ordered {
    private String[] skipAuthUrls;
    /**
     * 过滤器逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求url地址
        String path = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if(skipAuthUrls!=null && isSKip(path)){
            return chain.filter(exchange);
        }
        // 1.从请求头中获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        // 2.判断token
        if(StringUtils.isEmpty(token)){
            // 3.设置响应
            ServerHttpResponse response = exchange.getResponse();
            // 4.设置响应状态码
            response.setStatusCode(HttpStatus.OK);
            // 5.设置响应头
            response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
            // 6.创建响应对象
            Response res = new Response(200, "token 参数缺失");
            // 7.对象转字符串
            byte[] bytes = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
            // 8.数据流返回数据
            DataBuffer wrap = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(wrap));

        }
        // 验证token
        boolean verify = JWTUtils.verify(token);
        if(!verify){
            // 3.设置响应
            ServerHttpResponse response = exchange.getResponse();
            // 4.设置响应状态码
            response.setStatusCode(HttpStatus.OK);
            // 5.设置响应头
            response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
            // 6.创建响应对象
            Response res = new Response(200, "token 失效");
            // 7.对象转字符串
            byte[] bytes = JSONObject.toJSONString(res).getBytes(StandardCharsets.UTF_8);
            // 8.数据流返回数据
            DataBuffer wrap = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(wrap));
        }
        // token 令牌通过
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private boolean isSKip(String url){
        for (String skipAuthUrl :skipAuthUrls) {
            if(url.startsWith(skipAuthUrl)){
                return true;
            }
        }
        return false;
    }
}
