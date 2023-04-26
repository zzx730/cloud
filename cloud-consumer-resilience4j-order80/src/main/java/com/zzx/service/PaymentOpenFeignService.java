package com.zzx.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 支付远程调用OpenFeign接口
 */
//根据服务生产者的名字调用
@FeignClient("CLOUD-PAYMENT-PROVIDER")
public interface PaymentOpenFeignService {
    @GetMapping("/payment/timeout")
    String timeout();
    @GetMapping("/payment/index")
    String index();
}
