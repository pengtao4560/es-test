package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 扇出模式实战-  消息接受
 */
public class ReceiveLog1 {

    private static String EXCHANGENAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel();
        // 声明一个交换机
        channel.exchangeDeclare(EXCHANGENAME, BuiltinExchangeType.FANOUT);
        // 声明一个 临时队列
        /**
         * 生成一个临时队列，队列的名称是随机的
         * 当消费者断开与队列的连接的时候，队列就自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        /**
         * 绑定交换机与队列
         */
        AMQP.Queue.BindOk bindOk = channel.queueBind(queueName, EXCHANGENAME, "");
        System.out.println("ReceiveLog1 等待接受消息，把接收到的消息打印在屏幕上......");

        DeliverCallback de = (consumerTag, message) -> {
            System.out.println("ReceiveLog1 控制台打印接收到的消息：" + new String(message.getBody(), "UTF-8"));
        };
        CancelCallback can = null;
        channel.basicConsume(queueName, true, de, can);

    }
}
