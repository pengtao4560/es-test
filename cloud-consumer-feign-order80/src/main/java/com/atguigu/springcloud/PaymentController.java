package com.atguigu.springcloud;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.Payment9001FeignService;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author pt
 * @createdate 2021/11/30 0030
 * @desc 消费者控制层
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @Autowired
    private Payment9001FeignService payment9001FeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openFeign 底层就是ribbon ，客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }

    @GetMapping(value = "/consumer/get")
    public String get() {
        // openFeign 底层就是ribbon ，客户端一般默认等待1秒钟
        return payment9001FeignService.getPayment(1);
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDate.now().atTime(LocalTime.MAX);
        LocalDateTime localDateTime1 = LocalDate.now().atTime(23, 59, 59);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
    }

}
