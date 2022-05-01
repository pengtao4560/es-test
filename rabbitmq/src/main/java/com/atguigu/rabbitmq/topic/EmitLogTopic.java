package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 主题模式demo- 生产者
 */
@Slf4j
public class EmitLogTopic {
    /** 交换机的名字 */
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtil.getChannel();

        HashMap<String, String> bindKeyMap = new HashMap<>();
        bindKeyMap.put("quick.orange.rabbit", "被队列 Q1Q2 接收到");
        bindKeyMap.put("lazy.orange.elephant", "被队列 Q1Q2 接收到");
        bindKeyMap.put("quick.orange.fox", "被队列 Q1 接收到");
        bindKeyMap.put("lazy.brown.fox", "被队列 Q2 接收到");
        bindKeyMap.put(" lazy.pink.rabbit", "虽然满足两个绑定但只被队列 Q2 接收一次");
        bindKeyMap.put("quick.brown.fox", "不匹配任何绑定不会被任何队列接收到会被丢弃");
        bindKeyMap.put("quick.orange.male.rabbit", "是四个单词不匹配任何绑定会被丢弃");
        bindKeyMap.put("lazy.orange.male.rabbit", "是四个单词但匹配 Q2");

        for (Map.Entry<String, String> bindingStringEntry : bindKeyMap.entrySet()) {
            String routingKey = bindingStringEntry.getKey();
            String message = bindingStringEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME, routingKey, null,
                    message.getBytes(StandardCharsets.UTF_8));
            log.info("生产者发出消息");
        }
    }
}
