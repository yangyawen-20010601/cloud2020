package com.bdqn.springcloud.controller;

import com.bdqn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-13 13:19
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentinfo(@PathVariable("id") Integer id){
        String s = paymentService.paymentInfo_OK(id);
        log.info("******result"+s);
        return s;
    }
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentinfotimeout(@PathVariable("id") Integer id){
        String s = paymentService.paymentInfo_timeout(id);
        log.info("******result"+s);
        return s;
    }
    //服务熔断
    @GetMapping(value = "/payment/circuit/{id}")
    public String rongduan(@PathVariable("id") Integer id){
        String s = paymentService.paymentCircuitBreaker(id);
        log.info("******result"+s);
        return s;
    }
}
