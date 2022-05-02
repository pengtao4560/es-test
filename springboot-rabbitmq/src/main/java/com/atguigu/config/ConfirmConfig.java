package com.atguigu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类 发布确认 （高级）
 */
@Configuration
public class ConfirmConfig {
    /**
     * 交换机
     */
    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    /**
     * 队列
     */
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";
    /**
     * routingKey
     */
    public static final String CONFIRM_ROUTING_KEY = "key1";

    /**
     * 声明交换机 Exchange
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    /**
     * 声明确认队列
     */
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    /**
     * 声明确认队列绑定关系
     */
    @Bean
    public Binding queueBinding(@Qualifier("confirmQueue") Queue queue,
                                @Qualifier("confirmExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CONFIRM_ROUTING_KEY);
    }
}
