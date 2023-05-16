package com.zzx.controller;

import com.zzx.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    /**
     * openfeign远程服务调用
     */
    @RequestMapping("index")
    public String index(){
        return paymentFeignService.index();
    }
    @GetMapping("/timeout")
    public String timeout() {
        return paymentFeignService.timeout();
    }
}
