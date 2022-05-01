package com.atguigu.rabbitmq.deadmessage;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

/**
 * 死信队列-生产者
 */
public class Producer {

    private static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] argv) throws Exception {

        Channel channel = RabbitmqUtil.getChannel();
        // 设置消息的 TTL time to live 时间 10000ms = 10s   (模拟死信)
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        //该信息是用作演示队列个数限制
        for (int i = 1; i < 11; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", properties, message.getBytes());
            System.out.println("生产者发送消息:" + message);
            // TimeUnit.SECONDS.sleep(3);
        }
    }
}
