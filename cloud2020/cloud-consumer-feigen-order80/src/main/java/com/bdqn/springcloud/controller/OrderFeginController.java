package com.bdqn.springcloud.controller;

import com.bdqn.springcloud.entities.CommonResult;
import com.bdqn.springcloud.entities.Payment;
import com.bdqn.springcloud.service.PaymentFeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-11 12:37
 **/
@RestController
@Slf4j
public class OrderFeginController {

    @Resource
    private PaymentFeginService paymentFeginService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        CommonResult<Payment> paymentById = paymentFeginService.getPaymentById(id);
        return paymentById;
    }
    @GetMapping(value = "/comsumer/payment/fegin/timeout")
    public String paymentFeginTimeout(){
        //客户端默认等待一秒钟
        return paymentFeginService.paymentFeginTimeout();
    }
}
