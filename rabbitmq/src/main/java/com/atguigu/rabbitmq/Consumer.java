package com.atguigu.rabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pt
 * @createdate 2022/3/20 0020
 * @desc 消费者 接收消息的
 */
public class Consumer {
    // 队列的名称

    public static final String QUEUE_NAME = "HELLO";

    // 接收消息
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = RabbitmqUtil.getConnectionFactory();

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // 声明接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            byte[] body = message.getBody();
            System.out.println(new String(body));
        };
        // 声明取消消息
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消费消息被中断");
        };

        /**
         * 消费者消费消息
         1.消费哪个队列
         2.消费成功之后是否要自动
         3.消费者未成功消费的回调
         4.消费者取消消费的回调
         5.应答true代表的自动应答false代表手动应答
         */


        String s = channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);

    }
}






