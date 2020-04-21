package com.bdqns.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-08 17:26
 **/
@Configuration
public class MySelfRule {
    @Bean
    public IRule myrule()
    {

        return new RandomRule();//随机
    }
}
