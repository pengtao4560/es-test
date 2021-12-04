package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author pt
 * @createdate 2021/12/02 0002
 * @desc 实现调用服务端所有方法的接口的实现类
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
