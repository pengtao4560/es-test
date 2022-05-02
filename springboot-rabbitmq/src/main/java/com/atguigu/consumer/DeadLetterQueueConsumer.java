package com.atguigu.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 队列TTL 消费者
 */
@Slf4j
@Component //( 需要交给spring，不然无法监听及消费消息)
public class DeadLetterQueueConsumer {

    // 接收消息
    @RabbitListener(queues = "QD")
    public void receiveD(Message message) {
        byte[] body = message.getBody();

        String msg = new String(message.getBody());
        log.info("当前时间： {} ,收到死信队列的消息： {}",
                new Date().toString(), msg);
    }
}
