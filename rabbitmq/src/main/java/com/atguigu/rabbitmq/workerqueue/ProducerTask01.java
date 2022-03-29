package com.atguigu.rabbitmq.workerqueue;

import com.atguigu.rabbitmq.constant.RabbitmqConstant;
import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author pt
 * @createdate 2022/3/21 0021
 * @desc
 * 生产者 发送大量的消息
 */
public class ProducerTask01 {

    // 队列名称
    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitmqUtil.getChannel();
        // 队列的声明
        // 需要持久化
        boolean durable = true;
        channel.queueDeclare(RabbitmqConstant.QUEUE_NAME, true, false, false, null);
        // 本次从控制台当中接受信息(控制台来发送消息)

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            /*
            发送一个消费
            param
            1.发送到哪个交换机 ""
            2.路由的Key值是哪个本次是队列的名称 RabbitmqConstant.QUEUE_NAME
            3.其它参数信息 null
            4.发送消息的消息体 message.getBytes()
            * */
            channel.basicPublish("", RabbitmqConstant.QUEUE_NAME, null, message.getBytes());
        }
    }
}
