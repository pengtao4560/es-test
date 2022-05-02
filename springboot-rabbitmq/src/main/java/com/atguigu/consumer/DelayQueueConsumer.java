package com.atguigu.consumer;

import com.atguigu.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 基于rabbitmq插件的延时队列-消费者
 */
@Component
@Slf4j
public class DelayQueueConsumer {

    /**
     * 监听消息
     */
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message) {
        String msg = new String(message.getBody());
        log.info("当前时间： {}， 收到延迟队列的消息：{}", new Date(), msg);
    }
}
