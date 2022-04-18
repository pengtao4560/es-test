package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 * 发消息 给 交换机
 */
public class EmitLog {

    private static String EXCHANGENAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitmqUtil.getChannel();
        channel.exchangeDeclare(EXCHANGENAME, "fanout");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(EXCHANGENAME, "", null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);

        }
    }
}
