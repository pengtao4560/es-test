package com.atguigu.rabbitmq.workerqueue;

import com.atguigu.rabbitmq.constant.RabbitmqConstant;
import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author pt
 * @createdate 2022/3/20 0020
 * @desc 这是一个工作线程。相当于之前消费者
 */
public class Worker01 {

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitmqUtil.getChannel();

        // 消息的接收
        String s = channel.basicConsume(RabbitmqConstant.QUEUE_NAME, true, RabbitmqUtil.getSimpleDeliverCallback(), RabbitmqUtil.getSimpleCancelCallback());

    }
}


