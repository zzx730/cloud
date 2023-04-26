package com.zzx.controller;

import com.zzx.service.PaymentOpenFeignService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 订单控制层
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    /**
     * 测试超时降级
     *
     * @return
     */
    @GetMapping("timeout")
    @TimeLimiter(name = "delay", fallbackMethod = "timeoutfallback")
    public CompletableFuture<String> timeout() {
        log.info("*********进入方法*********");
        //异步操作
        CompletableFuture<String> timeout = CompletableFuture.supplyAsync((Supplier<String>)()->paymentOpenFeignService.timeout());
        log.info("*********离开方法*********");
        return timeout;
    }

    /**
     * 超时服务降级方法
     * @param e
     * @return
     */
    public CompletableFuture<ResponseEntity> timeoutfallback(Exception e){
        e.printStackTrace();
        return CompletableFuture.completedFuture(ResponseEntity.ok("读取超时"));
    }
    /**
     * 重试
     * @return
     */
    @GetMapping("retry")
    @Retry(name = "backendA")
    public CompletableFuture<String> retry() {
        log.info("*********进入方法*********");
        //异步操作
        CompletableFuture<String> timeout = CompletableFuture.supplyAsync((Supplier<String>)()->paymentOpenFeignService.timeout());
        log.info("*********离开方法*********");
        return timeout;
    }
    /**
     * 熔断降级
     * @return
     */
    @GetMapping("circuitbreaker")
    @CircuitBreaker(name = "backendA",fallbackMethod = "circuitbreakerfallback")
    public String circuitbreaker() {
        log.info("*********进入方法*********");
        String index = paymentOpenFeignService.index();
        log.info("*********离开方法*********");
        return index;
    }
    public String circuitbreakerfallback(Exception e){
        e.printStackTrace();
        return "服务器繁忙，请稍后重试";
    }
    /**
     * 慢调用熔断降级
     * @return
     */
    @GetMapping("slowcircuitbreaker")
    @CircuitBreaker(name = "backendB",fallbackMethod = "slowcircuitbreakerfallback")
    public String slowcircuitbreaker() {
        log.info("*********进入方法*********");
        String index = paymentOpenFeignService.timeout();
        log.info("*********离开方法*********");
        return index;
    }
    public String slowcircuitbreakerfallback(Exception e){
        e.printStackTrace();
        return "正在处理，请稍等";
    }
    /**
     * 信号量隔离
     * @return
     */
    @GetMapping("bulkhead")
    @Bulkhead(name = "backendA",type = Bulkhead.Type.SEMAPHORE)
    public String bulkhead() throws InterruptedException {
        log.info("*********进入方法*********");
        TimeUnit.SECONDS.sleep(10);
        String index = paymentOpenFeignService.timeout();
        log.info("*********离开方法*********");
        return index;
    }
    /**
     * 线程池隔离
     * @return
     */
    @GetMapping("thread")
    @Bulkhead(name = "backendA",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture future() throws InterruptedException {
        log.info("*********进入方法*********");
        TimeUnit.SECONDS.sleep(5);
        String index = paymentOpenFeignService.timeout();
        log.info("*********离开方法*********");
        return CompletableFuture.supplyAsync(()->"信号量");
    }
    /**
     * 限流
     * @return
     */
    @GetMapping("rate")
    @RateLimiter(name = "backendA")
    public String rateLimiter() throws InterruptedException {
        log.info("*********进入方法*********");
        TimeUnit.SECONDS.sleep(5);
        //异步操作
        String index = paymentOpenFeignService.index();
        log.info("*********离开方法*********");
        return index;
    }
}
