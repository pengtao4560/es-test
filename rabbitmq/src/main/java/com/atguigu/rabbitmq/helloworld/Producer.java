package com.atguigu.rabbitmq.helloworld;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pt
 * @createdate 2022/3/20 0020
 * @desc 生产者-发消息
 */
@Component
public class Producer {


    public static final String QUEUE_NAME = "HELLO";
//    @Value("${rabbitmq.host}")
//    private static String host;
//    @Value("${rabbitmq.userName}")
//    private static String userName;
//    @Value("${rabbitmq.password}")
//    private static String password;

    /**
     * @see com.rabbitmq.client.Channel#queueDeclare(java.lang.String, boolean, boolean, boolean, java.util.Map)
     */
    @Test
    public void test1() {


    }

    public static void main(String[] args) {
        mockSetMessage();
    }

    public static void mockSetMessage() {
        ConnectionFactory connectionFactory = RabbitmqUtil.getConnectionFactory();

        try {
            // 创建链接
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // 生成一个队列
      /*
      @param queue the name of the queue 队列名称

      @param durable true if we are declaring a durable queue (the queue will survive a server restart)
      队列里面的消息是否持久化（磁盘）默认情况消息存储在内存中

      @param exclusive true if we are declaring an exclusive queue (restricted to this connection)
      该队列是否只供一个消费者进行消费是否进行消息共享，tru可以多个消费者消费fa1se:只能一个消费者消费

      @param autoDelete true if we are declaring an autodelete queue (server will delete it when no longer in use)
      是否自动删除最后一个消费者段开连接以后，该队列是否自动删除  true表示自动删除 false 表示不自动删除

      @param arguments other properties (construction arguments) for the queue
     其他參數
     * */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //  发消息
            String message = "hello world";
            /*
            Publish a message. Publishing to a non-existent exchange will result in a channel-level protocol exception, which closes the channel.
            Invocations of Channel#basicPublish will eventually block if a resource-driven alarm  is in effect.
            发送一个消息
            Params:
            exchange – the exchange to publish the message to  发送到哪个交换机
            routingKey – the routing key 路由的key是哪一个 （当前demo 是队列的名称）
            props – other properties for the message - routing headers etc 其他参数信息 （当前demo）
            body – the message body 发送消息的消息体
            Throws:
            IOException – if an error is encountered
            * */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            // 防火墙的端口记得放行并reload  5672、15672
            System.out.println("消息发送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
