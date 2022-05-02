package com.atguigu.config;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 延迟队列配置（rabbitmq之基于插件的延迟队列-配置类）
 */
@Configuration
public class DelayedQueueConfig {

    /**
     * 交换机
     */
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    /**
     * 队列
     */
    public static final String DELAYED_EXCHANGE = "delayed.exchange";

    /**
     * routingKey
     */
    public static final String DELAYED_ROUTINGKEY = "delayed.routingkey";

    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    /**
     * 声明交换机
     * @see CustomExchange#CustomExchange(java.lang.String, java.lang.String, boolean, boolean, java.util.Map);
     * 参数1. 交换机名称
     * 参数2. 交换机类型
     * 参数3. 是否需要持久化
     * 参数4. 是否需要自动删除
     * 参数5. 其他参数map
     */

    @Bean
    public CustomExchange delayedExchange() {
        HashMap<String, Object> arguements = new HashMap<>();
        arguements.put("x-delayed-type", BuiltinExchangeType.DIRECT);

        return new CustomExchange(DELAYED_QUEUE_NAME, "x-delayed-message", true, false, arguements);
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
