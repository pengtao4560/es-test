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

    /**
     * @see <a href="http://localhost:8672/confirm/sendMessage/ok"></a>
     * @see <a href="http://localhost:8672/confirm/sendMessage/noExchange"></a>
     * @see <a href="http://localhost:8672/confirm/sendMessage/routingError"></a>
     * @param message
     */
    @GetMapping("/sendMessage/{message}")
    public String sendMessage(@PathVariable String message){
        String result = "其他情况";
        //指定消息 id 为 1
        CorrelationData correlationData1 = new CorrelationData("1");
        String routingKey = "key1";
        // ---------模拟正确 生产者------------
        if ("ok".equalsIgnoreCase(message)) {
            log.info("---------模拟正确 生产者------------");
            rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, routingKey, message + routingKey, correlationData1);
            result = "模拟正确 生产者";
        }
        if ("noExchange".equalsIgnoreCase(message)) {
            log.info("---------模拟发送失败情况 交换机不存在 no exchange ------------");
            rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME + UUID.randomUUID(), routingKey, message + routingKey, correlationData1);
            result = "模拟发送失败情况 交换机不存在";
        }


        // 模拟发送失败情况： 队列不正确（routing key 不存在）

        if ("routingError".equalsIgnoreCase(message)) {
            log.info("---------模拟发送失败情况： 1. 队列不正确（routing key 不存在） 2. 报警------------");
            rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                    ConfirmConfig.CONFIRM_ROUTING_KEY + UUID.randomUUID(), message);
            result = "1. 模拟发送失败情况： 队列不正确（routing key 不存在）需要将：ConfirmConfig中该行注释放开 // return new DirectExchange(CONFIRM_EXCHANGE_NAME);" +
                    "\n 2. 模拟无法投递的消息发送给备份交换机 报警消费者 ";
        }
        // mandatory 参数与备份交换机可以一起使用的时候，如果两者同时开启，消息究竟何去何从？谁优先级高，经过上面结果显示答案是备份交换机优先级高。
        log.info("发送消息内容:{}", message);
        return result;
    }
}
