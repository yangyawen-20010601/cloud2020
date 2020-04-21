package com.bdqn.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-04-10 09:40
 **/
@Component//很有用。我理解的是 @Configuration是随容器启动开始加载的,始终存在的单例模式。 @Component是使用一次即实例化一次
public class lb implements LoadBalancer {

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public final int getAndIcrement(){
        int current;
        int next;
        current= atomicInteger.get();
        do {
            next=current>=2000000 ? 0 : current+1;

            System.out.println(current);
            System.out.println(next);
        }while (!this.atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance instacne(List<ServiceInstance> serviceInstances) {
        int andIcrement = getAndIcrement();
        int i = andIcrement % serviceInstances.size();
        return serviceInstances.get(i);
    }
}
