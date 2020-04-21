package com.bdqn.springcloud.controller;

import com.bdqn.springcloud.entities.CommonResult;
import com.bdqn.springcloud.entities.Payment;
import com.bdqn.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-03-30 10:11
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    //@Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    //@requestBody可以将请求体中的JSON字符串绑定到相应的bean上，当然，也可以将其分别绑定到对应的字符串上
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        Long id = payment.getId();
        log.info("*****插入结果:"+i);
        if(i>0){
            return new CommonResult(200,"插入成功,端口号为+"+serverPort+"+,新的数据id:"+id,i);
        }else{
            return new CommonResult(444,"插入失败",null);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*****查询结果:"+paymentById);
        if(paymentById!=null){
            return new CommonResult(200,"查询成功,端口号为+"+serverPort+"+",paymentById);
        }else{
            return new CommonResult(444,"查询失败,id:"+id,null);
        }

    }
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****服务清单"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(""+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String  lbs(){
        return serverPort;
    }
    @GetMapping(value = "/payment/fegin/timeout")
    public String paymentFeginTimeout(){

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
