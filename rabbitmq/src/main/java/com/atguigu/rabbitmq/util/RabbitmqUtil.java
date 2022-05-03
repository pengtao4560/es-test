package com.atguigu.rabbitmq.util;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq连接工厂创建信道的工具类
 * @author pt
 * @createdate 2022/3/20 0020
 * @desc rabbitmq 创建工厂简单工具类
 */
public class RabbitmqUtil {

    public static ConnectionFactory getConnectionFactory() {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 工厂IP 连接RABBITMOQ 队列
        String host = "192.168.220.129";
        String userName = "admin";
        String password = "admin";
        connectionFactory.setHost(host);
        connectionFactory.setPort(5672);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setHandshakeTimeout(30000);
        return connectionFactory;
    }

    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = getConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }

    // 声明接收消息
    DeliverCallback deliverCallback = (consumerTag, message) -> {
        byte[] body = message.getBody();
        System.out.println(new String(body));
    };
    public static DeliverCallback getSimpleDeliverCallback() {
           // 声明接收消息
           DeliverCallback deliverCallback = (consumerTag, message) -> {
            byte[] body = message.getBody();
            System.out.println("接收到的消息： " + new String(body));
        };
        return deliverCallback;
    }


    public static CancelCallback getSimpleCancelCallback() {
        // 声明接收消息
        // 声明取消消息
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + " 消息者取消消费接口回调逻辑");
        };
        return cancelCallback;
    }
}
