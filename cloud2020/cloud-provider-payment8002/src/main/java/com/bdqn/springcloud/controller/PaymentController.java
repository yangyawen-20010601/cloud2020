package com.bdqn.springcloud.controller;

import com.bdqn.springcloud.entities.CommonResult;
import com.bdqn.springcloud.entities.Payment;
import com.bdqn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @GetMapping(value = "/payment/lb")
    public String  lbs(){
        return serverPort;
    }
}
