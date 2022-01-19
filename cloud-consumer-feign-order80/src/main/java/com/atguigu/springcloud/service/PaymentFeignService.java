package com.atguigu.springcloud.service;

import com.atguigu.springcloud.config.FeignConfig;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author pt
 * @date 2021/11/30 0030 - 20:57
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE", configuration = FeignConfig.class)
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
