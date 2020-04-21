package com.bdqn.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-03 13:09
 **/
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverprot;

    @GetMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springcloud with zk"+serverprot+"\t"+ UUID.randomUUID().toString();
    }
}
