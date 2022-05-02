package com.atguigu.controller;

import com.atguigu.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 开始发消息 测试确认
 */
@RestController
@RequestMapping("/confirm/")
@Slf4j
public class ProducerController {

    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    @Autowired
    private RabbitTemplate rabbitTemplate;
/*    @Autowired
    private MyCallBack myCallBack;

    //依赖注入 rabbitTemplate 之后再设置它的回调对象
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(myCallBack);
    }*/

    /**
     * @see <a href="http://localhost:8672/confirm/sendMessage/dajiahao1"></a>
     * @param message
     */
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message){
        //指定消息 id 为 1
        CorrelationData correlationData1 = new CorrelationData("1");
        String routingKey = "key1";
        // 模拟正确 生产者
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData1);
        // 模拟发送失败情况 交换机不存在
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME + UUID.randomUUID(), routingKey, message + routingKey, correlationData1);

        // 模拟发送失败情况： 队列不正确（routing key 不存在）
        CorrelationData correlationData2 = new CorrelationData("2");
        routingKey = "key2";

        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData2);

        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY, message);

        log.info("发送消息内容:{}", message);
    }
}
