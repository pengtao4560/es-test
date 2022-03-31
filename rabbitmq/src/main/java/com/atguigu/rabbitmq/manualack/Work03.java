package com.atguigu.rabbitmq.manualack;

import cn.hutool.core.thread.ThreadUtil;
import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息手动应答-消费者1
 *
 *  * 消息在手动应答时是不丢失的，放回队列中重新消费
 */
@Slf4j
public class Work03 {
    // 队列名称
    public static final String TASK_QUEUE_NAME = "ACK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel();
        // 开启发布确认
        channel.confirmSelect();
        log.info("C1 等待接受消息处理时间较短");

        // 设置公平分发
        // channel.basicQos(0);

        // 设置不公平分发
        // channel.basicQos(1);
        // 设置预取值
        channel.basicQos(2);
        // 采用手动应答
        boolean autoAck = false;
        DeliverCallback deliverCallBack = ((consumerTag, message) -> {
            // 沉睡一秒钟
            ThreadUtil.sleep(1000);
            log.info("接收到的消息： {}", new String(message.getBody(), "UTF-8"));

            //手动应答的代码
            /**
             * 1.消息的标记 tag
             * 2. 是否批量应答(不应该，否则可能有消息丢失的风险) false 不批量应答信道中的消息
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        });

        channel.basicConsume(TASK_QUEUE_NAME, autoAck, deliverCallBack, (consumerTag -> {
            log.info("消费者取消消费接口回调逻辑");
        }));
    }
}
