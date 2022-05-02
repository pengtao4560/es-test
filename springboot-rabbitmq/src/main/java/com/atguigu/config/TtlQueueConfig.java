package com.atguigu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * TTL time to live 队列  配置文件类
 *
 * @see /代码架构图.png
 */
@Configuration
public class TtlQueueConfig {

    /**
     * 普通交换机的名称
     */
    public static final String X_EXCHANGE = "X";
    /**
     * 死信交换机的名称
     */
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";

    /**
     * 普通队列名称
     */
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    /**
     * 死信队列名称
     */
    public static final String DEAD_LETTER_QUEUE = "QD";

    /**
     * 普通队列的名称
     */
    public static final String QUEUE_C = "QC";

    /**
     * 声明xChange
     */
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    /**
     * 声明xChange
     */
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明队列 queueA, TTL 为 10s
     */
    @Bean("queueA")
    public Queue queueA() {
        HashMap<String, Object> arguements = new HashMap<>();
        // 设置死信交换机
        arguements.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 设置死信 RoutingKey
        arguements.put("x-dead-letter-routing-key", "YD");
        arguements.put("x-message-ttl", 5000);
        return QueueBuilder.durable(QUEUE_A).withArguments(arguements).build();
    }

    /**
     * 声明队列 queueB, TTL 为 40s
     */
    @Bean("queueB")
    public Queue queueB() {
        HashMap<String, Object> arguements = new HashMap<>();
        // 设置死信交换机
        arguements.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 设置死信 RoutingKey
        arguements.put("x-dead-letter-routing-key", "YD");
        arguements.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_B).withArguments(arguements).build();
    }

    /**
     * 声明队列 queueB, TTL 为 40s
     */
    @Bean("queueC")
    public Queue queueC() {
        HashMap<String, Object> arguements = new HashMap<>();
        // 设置死信交换机
        arguements.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 设置死信 RoutingKey
        arguements.put("x-dead-letter-routing-key", "YD");
        // 不写ttl 存活时间
        return QueueBuilder.durable(QUEUE_C).withArguments(arguements).build();
    }

    /**
     * 声明死信队列 queueD, TTL 为 40s
     */
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    // 2 个交换机，3个队列 以及绑定关系
    /*绑定*/
    @Bean
    public Binding queueABindingX(
            @Qualifier("queueA") Queue queueA,
            @Qualifier("xExchange") DirectExchange xExchange
    ) {
        String routingKey = "XA";
        return BindingBuilder.bind(queueA)
                .to(xExchange).with(routingKey);
    }

    @Bean
    public Binding queueBBindingX(
            @Qualifier("queueB") Queue queueB,
            @Qualifier("xExchange") DirectExchange xExchange
    ) {
        String routingKey = "XB";
        return BindingBuilder.bind(queueB)
                .to(xExchange).with(routingKey);
    }

    @Bean
    public Binding queueDBindingY(
            @Qualifier("queueD") Queue queueD,
            @Qualifier("yExchange") DirectExchange yExchange
    ) {
        String routingKey = "YD";
        return BindingBuilder.bind(queueD)
                .to(yExchange).with(routingKey);
    }

    @Bean
    public Binding queueCBindingX(
            @Qualifier("queueC") Queue queueC,
            @Qualifier("xExchange") DirectExchange xExchange
    ) {
        String routingKey = "XC";
        return BindingBuilder.bind(queueC)
                .to(xExchange).with(routingKey);
    }
}
