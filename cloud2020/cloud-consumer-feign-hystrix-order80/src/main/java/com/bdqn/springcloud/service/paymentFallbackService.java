package com.bdqn.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-14 14:12
 **/
@Component
public class paymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentinfo(Integer id) {
        return "paymentFallbackService失败";
    }

    @Override
    public String paymentinfotimeout(Integer id) {
        return "paymentFallbackService超时失败";
    }
}
