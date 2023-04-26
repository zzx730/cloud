package com.zzx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    //服务发现
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 获取服务的列表清单
     */
    @GetMapping("/discovery")
    public Object testDiscoveryClient() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        return this.discoveryClient;
    }

    @GetMapping("/index")
    public String index() {
        //服务生产者名字
        String hostname = "CLOUD-PAYMENT-PROVIDER";
        //远程调用方法具体URL
        String url = "/payment/index";
        //服务发现中获取服务生产者实例
        List<ServiceInstance> instances = discoveryClient.getInstances(hostname);
        //获取具体实例 服务生产者实例
        ServiceInstance serviceInstance = instances.get(0);
        //发起远程调用
        //getForObject：返回响应体中数据转化成的对象，可以理解为json
        //getForEntity：返回的是ResponseEntity的对象包含了一些重要的信息
        String forObject = restTemplate.getForObject(serviceInstance.getUri()+url, String.class);
        System.out.println(forObject);
        return forObject;
    }


}
