package com.bdqn.springcloud.service.impl;

import com.bdqn.springcloud.dao.PaymentDao;
import com.bdqn.springcloud.entities.CommonResult;
import com.bdqn.springcloud.entities.Payment;
import com.bdqn.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-03-30 10:07
 **/
@Service
@DefaultProperties(defaultFallback = "fallback_method")
public class PaymentServiceImpl implements PaymentService {

    //@Autowired //spring
    @Resource  //java自带的依赖注入
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback_method", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "2000")
    })
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
    public Payment fallback_method(Long id){
        return new Payment(null,"降级");
    }
}
