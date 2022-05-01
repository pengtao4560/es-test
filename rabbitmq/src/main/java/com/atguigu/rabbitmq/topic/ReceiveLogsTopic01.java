package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题模式demo 消费者C1
 * 主题交换机 及相关队列
 * 消费者C1
 */
@Slf4j
public class ReceiveLogsTopic01 {
    /** 交换机的名字 */
    public static final String EXCHANGE_NAME = "topic_logs";

    // 接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        // 声明队列
        String queueName = "Q1";

        channel.queueDeclare(queueName, false, false, false, null);

        String routingKey1 = "*.orange.*";
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey1);
        System.out.println("消费者C1 等待接受消息");

        DeliverCallback deliverCallback =  (consumerTag,  message) -> {
            log.info("------------");
            log.info(new String(message.getBody(), "UTF-8"));
            // envelope 封套（包装信息） Encapsulates a group of parameters used for AMQP's Basic methods 封装一组用于AMQP的基本方法的参数
            log.info("接收队列：{} 绑定键 {}", queueName, message.getEnvelope().getRoutingKey());
            log.info("-------------");

        };
        CancelCallback cancelCallback = (consumerTag) ->{};

        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);
    }
}
