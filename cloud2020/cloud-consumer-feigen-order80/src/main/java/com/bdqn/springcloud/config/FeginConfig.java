package com.bdqn.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-12 12:23
 **/
@Configuration //开启fegin日志
public class FeginConfig
{
    @Bean
    Logger.Level feginLoggerLevel()
    {
        return Logger.Level.FULL;
    }
}
