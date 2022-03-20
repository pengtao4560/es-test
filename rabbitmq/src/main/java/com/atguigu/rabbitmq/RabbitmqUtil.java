package com.atguigu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
        String host = "192.168.220.128";
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
}
