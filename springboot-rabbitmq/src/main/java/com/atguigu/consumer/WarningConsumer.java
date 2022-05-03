package com.atguigu.consumer;

import com.atguigu.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 备份交换机demo 中的  报警消费者
 */
@Component
@Slf4j
public class WarningConsumer {

    /**
     * 接收报警消息
     */
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public void receiveWarningMsg(Message message) {
        String msg = new String(message.getBody());
        log.error("报警发现不可路由消息： {}", msg);
    }
}
