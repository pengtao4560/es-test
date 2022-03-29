package com.atguigu.rabbitmq.manualack;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 消息手动应答-生产者
 *
 * 消息在手动应答时是不丢失的，放回队列中重新消费
 */
@Slf4j
public class Task2 {

    // 队列名称
    public static final String TASK_QUEUE_NAME = "ACK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel();
        // 需要持久化
        boolean durable = true;
        // 声明队列
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        // 从控制台中输入信息

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            // 设置生产者发送消息为持久化消息(要求保存到磁盘上) 否则保存在内存中
            AMQP.BasicProperties persistentTextPlain = MessageProperties.PERSISTENT_TEXT_PLAIN;
            channel.basicPublish("", TASK_QUEUE_NAME, persistentTextPlain, message.getBytes(StandardCharsets.UTF_8));
            log.info("生产者发出消息：{}", message);
        }
    }
}
