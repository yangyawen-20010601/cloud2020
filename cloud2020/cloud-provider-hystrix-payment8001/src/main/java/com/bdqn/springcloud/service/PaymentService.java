package com.bdqn.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;


/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-13 12:26
 **/
@Service
public class PaymentService {
    /**
     * @param:
     * @return:
     **/
    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentinfo_ok,id:"+id+"\t"+"O(∩_∩)O";
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_timeouthander",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_timeout(Integer id){
        //int a=10/0;
        int num=3;
        try { TimeUnit.SECONDS.sleep(num); }catch (InterruptedException e){e.printStackTrace();}
        return "线程池"+Thread.currentThread().getName()+"paymentinfo_tiemout,id:"+id+"\t"+"O(∩_∩)O"+"耗时";
    }

    public String paymentInfo_timeouthander(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_timeouthander,id:"+id+"\t"+"┭┮﹏┭┮";
    }

    //服务器熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds" , value = "10000"),//休眠时间
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage" , value = "60"),//断路频率
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if(id<0){
            throw new RuntimeException("*****id不能为负数");
        }
        String s = IdUtil.simpleUUID();//跟jdk的uuid.tostring一样
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+s;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id")Integer id){
        return "id 不能为负数,请稍后再试/(ㄒoㄒ)/~~   id:"+id;
    }

}
