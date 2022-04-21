package com.atguigu.rabbitmq.direct;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class ReceliveLogsDirect01 {

    public static final String EXCHANGENAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel();
        // 声明一个交换机
        channel.exchangeDeclare(EXCHANGENAME, BuiltinExchangeType.DIRECT);

        String queueName = "console";
        channel.queueDeclare(queueName, false, false, false, null);

        channel.queueBind(queueName, EXCHANGENAME, "info");
        channel.queueBind(queueName, EXCHANGENAME, "warning");

        DeliverCallback de = (consumerTag, message) -> {
            System.out.println("ReceliveLogsDirect01 控制台打印接收到的消息：" + new String(message.getBody(), "UTF-8"));
        };
        CancelCallback cancel = null;
        channel.basicConsume(queueName, true, de, cancel);
    }
}
