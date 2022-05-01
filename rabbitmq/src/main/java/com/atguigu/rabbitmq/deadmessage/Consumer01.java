package com.atguigu.rabbitmq.deadmessage;

import com.atguigu.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列 消费者1
 */
@Slf4j
public class Consumer01 {

    //普通交换机名称
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机名称
    private static final String DEAD_EXCHANGE = "dead_exchange";

    //普通队列的名称
    public static final String NORMAL_QUEUE = "normal_queue";
    //死信队列的名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] argv) throws Exception {

        Channel channel = RabbitmqUtil.getChannel();

        // 声明死信交换机和普通交换机 类型为 direct
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT, false);
        channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT, false);
        // 声明普通队列
        // exclusive 独占。  独占队列，仅限于此连接
        Map<String, Object> arguments = new HashMap<>();
        // 过期时间     10000ms = 10s
        arguments.put("x-message-ttl", 10000);
        // 正常队列设置 死信交换机是谁
        // x-dead-letter-exchange 是固定字符串

        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        // 设置死信RoutingKey
        arguments.put("x-dead-letter-routing-key", "lisi");

        //--------------------------------------
        // 绑定普通交换机与队列
        String routingKey = "zhangsan";
        String routingKey2 = "lisi";
        channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, routingKey);

        // 绑定死信交换机与队列
        channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, routingKey2);
        // 绑定死信的交换机与死信的队列
        log.info("等待接受消息。。。。。");
        channel.queueDeclare(NORMAL_QUEUE, false, false, false, null);
        // 声明死信队列
        channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

        DeliverCallback deliverCallback =  (consumerTag, message) -> {
            String messageStr = new String(message.getBody(), "UTF-8");
            log.info("Consumer01 接收到消息 {}", messageStr);
        };

        CancelCallback cancelCallback = (consumerTag) -> {

        };
        channel.basicConsume(NORMAL_QUEUE, true, deliverCallback, cancelCallback);
    }
}
