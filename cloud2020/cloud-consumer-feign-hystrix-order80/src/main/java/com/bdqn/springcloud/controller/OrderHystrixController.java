package com.bdqn.springcloud.controller;

import com.bdqn.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-13 16:48
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping(value = "/comsumer/payment/hystrix/ok/{id}")

    public String paymentinfo(@PathVariable("id") Integer id){
        String paymentinfo = paymentHystrixService.paymentinfo(id);
        return paymentinfo;
    }

    @GetMapping(value = "/comsumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeouthander",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String paymentinfotimeout(@PathVariable("id") Integer id){
        //int a=10/0;
        String paymentinfotimeout = paymentHystrixService.paymentinfotimeout(id);
        return paymentinfotimeout;
    }
    public String paymentInfo_timeouthander(Integer id){
        return "消费不行了挂,线程池"+Thread.currentThread().getName()+"paymentInfo_timeouthander,id:"+id+"\t"+"┭┮﹏┭┮";
    }
//    //全局fallback
    public String payment_Global_FallbackMethod(){
        return "处理异常，等哈再试";
    }
}
