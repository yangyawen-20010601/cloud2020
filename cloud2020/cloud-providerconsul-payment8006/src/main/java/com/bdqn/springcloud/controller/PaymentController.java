package com.bdqn.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-07 14:41
 **/
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;


    @RequestMapping(value = "/payment/consul")
    public String paymentzk(){
        return "springcloud with zk"+port+"\t"+ UUID.randomUUID().toString();
    }
}
