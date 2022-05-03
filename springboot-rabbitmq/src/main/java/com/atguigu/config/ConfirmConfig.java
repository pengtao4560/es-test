package com.atguigu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类 发布确认 （高级）以及  备份交换机
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

    /**备份交换机*/
    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";
    /**备份队列*/
    public static final String BACKUP_QUEUE_NAME = "backup_queue";
    /** 报警队列*/
    public static final String WARNING_QUEUE_NAME = "warning_queue";

    /**
     * 声明交换机 Exchange
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        // return new DirectExchange(CONFIRM_EXCHANGE_NAME);
        //无法投递的消息将发送给备份交换机  alternate备用
        // alternate-exchange 已知固定写法，TODO 从哪里来的？
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME).build();
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

    /**
     * 备份交换机
     * */
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }

    /**
     * 备份队列
     */

    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    /**
     * 备份队列
     */

    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    /**
     * 备份交换机和备份队列绑定
     */
    @Bean
    public Binding backupQueueBindingtoBackupExchange(
            @Qualifier("backupExchange") FanoutExchange backupExchange,
            @Qualifier("backupQueue") Queue backupQueue
    ) {
        // 扇出类型不需要 routingKey
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    /**
     * 备份交换机和报警队列绑定
     */
    @Bean
    public Binding warningQueueBindingtoBackupExchange(
            @Qualifier("backupExchange") FanoutExchange backupExchange,
            @Qualifier("warningQueue") Queue warningQueue
    ) {
        // 扇出类型不需要 routingKey
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }

}
