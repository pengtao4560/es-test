package com.atguigu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列配置（rabbitmq之基于插件的延迟队列-配置类）
 */
@Configuration
public class DelayedQueueConfig {
    /**
     * 队列
     */
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    /**
     * 交换机
     */
    public static final String DELAYED_EXCHANGE = "delayed.exchange";

    /**
     * routingKey
     */
    public static final String DELAYED_ROUTINGKEY = "delayed.routingkey";



    /**
     * 声明交换机
     * @see CustomExchange#CustomExchange(java.lang.String, java.lang.String, boolean, boolean, java.util.Map);
     * 参数1. 交换机名称
     * 参数2. 交换机类型
     * 参数3. 是否需要持久化
     * 参数4. 是否需要自动删除
     * 参数5. 其他参数map
     */

    @Bean("delayedExchange")
    public CustomExchange delayedExchange() {
        Map<String, Object> arguements = new HashMap<>();
        // arguements.put("x-delayed-type", BuiltinExchangeType.DIRECT);
        // 此处必须写字符串， 无法写枚举类型。否则 交换机和队列无法初始化 到 rabbitmq中
        arguements.put("x-delayed-type", "direct");

        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", true, false, arguements);
    }

    @Bean("delayedQueue")
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding delayedQueueBindingToDelayedExchange(
            @Qualifier("delayedQueue") Queue delayedQueue,
            @Qualifier("delayedExchange") CustomExchange delayedExchange
    ) {
        return BindingBuilder.bind(delayedQueue)
                .to(delayedExchange)
                .with(DELAYED_ROUTINGKEY)
                .noargs();
    }

}
