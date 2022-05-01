package com.atguigu.rabbitmq.deadmessage;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

/**
 * 死信队列 消费者2
 */
@Slf4j
public class Consumer02 {

    //普通交换机名称
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机名称
    private static final String DEAD_EXCHANGE = "dead_exchange";

    //普通队列的名称
    public static final String NORMAL_QUEUE = "normal_queue";
    //死信队列的名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] argv) throws Exception {

        Channel channel = RabbitmqUtil.getChannel();

        DeliverCallback deliverCallback =  (consumerTag, message) -> {
            String messageStr = new String(message.getBody(), "UTF-8");
            log.info("Consumer02 接收到消息 {}", messageStr);
        };

        CancelCallback cancelCallback = (consumerTag) -> {
        };

        channel.basicConsume(NORMAL_QUEUE, true, deliverCallback, cancelCallback);
    }
}
