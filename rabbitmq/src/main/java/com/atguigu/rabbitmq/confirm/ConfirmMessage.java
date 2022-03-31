package com.atguigu.rabbitmq.confirm;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * 发布确认模式
 * 使用的时间 比较哪种确认方式是最好的
 *
 * 1.单个确认模式
 * 2.批量确认模式
 * 3.异步确认
 */
@Slf4j
public class ConfirmMessage {

    // 批量发消息的个数
    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        // 1. 单个确认
        // publicMessageIndividually(); //
        // 22:01:52.420 [main] INFO com.atguigu.rabbitmq.confirm.ConfirmMessage - 发布 1000 个单独确认消息，耗时 705 毫秒(ms)

        // 2. 批量确认
        // publicMessageBatch(); // 弊端：无法确认哪个消息没有被确认
        // 22:10:23.019 [main] INFO com.atguigu.rabbitmq.confirm.ConfirmMessage - 发布 1000 个单独确认消息，耗时 148 毫秒(ms)

        // 3. 异步批量确认
        publishMessageAsync();
        // 22:43:55.025 [main] INFO com.atguigu.rabbitmq.confirm.ConfirmMessage - 发布 1000 个 异步发布确认消息，耗时 47 毫秒(ms)
    }

    /**
     * 单个确认
     */
    public static void publicMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitmqUtil.getChannel();
        // 队列的声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false,  null);
        // 开启发布确认
        channel.confirmSelect();
        // 开始时间
        long begin = System.currentTimeMillis();

        // 批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            // 单个消息就马上进行发布确认
            boolean confirmed = channel.waitForConfirms();
            if (confirmed) {
                log.info("消息发送成功");
            }
        }

        long end = System.currentTimeMillis();
        log.info("发布 {} 个 批量确认消息，耗时 {} 毫秒(ms)", MESSAGE_COUNT, end-begin);

    }

    /**
     * 批量发布确认
     */
    public static void publicMessageBatch() throws Exception {
        Channel channel = RabbitmqUtil.getChannel();
        // 队列的声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false,  null);
        // 开启发布确认
        channel.confirmSelect();
        // 开始时间
        long begin = System.currentTimeMillis();

        // 批量确认消息的大小
        int batchSize = 100;

        // 批量发送消息 批量发布确认
        for (int i = 0; i < MESSAGE_COUNT; i++) {

            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            // 发布确认

            // 判断达到100条消息的时候 批量确认一次
            if (i % batchSize == 0) {
                channel.waitForConfirms();
            }

        }
        long end = System.currentTimeMillis();
        log.info("发布 {} 个单独确认消息，耗时 {} 毫秒(ms)", MESSAGE_COUNT, end-begin);
    }

    /**
     * 异步发布确认
     */
    public static void publishMessageAsync() throws Exception {
        Channel channel = RabbitmqUtil.getChannel();
        // 队列的声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false,  null);
        // 开启发布确认
        channel.confirmSelect();
        // 开始时间
        long begin = System.currentTimeMillis();

        // 准备消息的监听器 监听哪些消息成功了，哪些消息失败了
        //消息确认成功 回调函数
        com.rabbitmq.client.ConfirmCallback ackCallback = (deliveryTag,  multiple) -> {
            log.info("确认的消息： {}", deliveryTag);
        };
        // 消息确认失败 回调函数
        /**
         * 1. deliveryTag 消息的标记
         * 2. multiple 是否为批量确认
         */
        ConfirmCallback nackCallback = (deliveryTag,  multiple) -> {
            log.info("未确认的消息： {}", deliveryTag);
        };
        /**
         * 监听哪些消息成功了
         * 监听哪些消息失败了
         */
        channel.addConfirmListener(ackCallback, nackCallback); //监听是 异步的通知
        // 批量发送消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {

            String message = "消息" + i;
            channel.basicPublish("", queueName, null, message.getBytes());
            // 发布确认
        }

        long end = System.currentTimeMillis();
        log.info("发布 {} 个 异步发布确认消息，耗时 {} 毫秒(ms)", MESSAGE_COUNT, end-begin);

    }
}
