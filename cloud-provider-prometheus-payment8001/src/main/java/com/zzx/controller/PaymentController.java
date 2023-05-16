package com.zzx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @RequestMapping("/index")
    public String index(){
        return "payment success";
    }
    @GetMapping("timeout")
    public String timeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "payment timeout";
    }
    @GetMapping("lb")
    public String lb(){
        return port;
    }
}
