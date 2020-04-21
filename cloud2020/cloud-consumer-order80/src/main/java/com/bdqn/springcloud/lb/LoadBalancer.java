package com.bdqn.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-10 09:36
 **/
public interface LoadBalancer {
        ServiceInstance instacne(List<ServiceInstance> serviceInstances);
}
