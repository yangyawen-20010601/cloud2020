package com.bdqn.springcloud.service.impl;

import com.bdqn.springcloud.dao.PaymentDao;
import com.bdqn.springcloud.entities.Payment;
import com.bdqn.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @program: cloud2020
 * @description
 * @author: Yang yawen
 * @create: 2020-03-30 10:07
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    //@Autowired //spring
    @Resource  //java自带的依赖注入
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
