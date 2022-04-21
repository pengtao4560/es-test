package com.atguigu.rabbitmq.direct;

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
public class DirectLog {

    private static String EXCHANGENAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitmqUtil.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();
            //channel.basicPublish(EXCHANGENAME, "info", null, message.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGENAME, "warning", null, message.getBytes("UTF-8"));
            //channel.basicPublish(EXCHANGENAME, "error", null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);

        }
    }
}
