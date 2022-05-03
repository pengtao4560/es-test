package com.atguigu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @see RabbitTemplate.ConfirmCallback
 */
@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    //
    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @PostConstruct // 在其他注解都执行完成后 进行执行
    public void init() {
        // 注入
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机确认回调方法
     * @param correlationData 中保存回调消息的ID及相关信息
     * @param ack 交换机收到消息 true / 交换机未收到消息 false
     * @param cause 交换机收到消息 null/ 交换机未收到消息 失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String id = Objects.isNull(correlationData) ? "" : correlationData.getId();
        if (ack) {
            log.info("交换机已经收到了ID为 {} 的消息", id);
        } else {
            log.info("交换机还未收到了ID为 {} 的消息, 由于原因： {}", id, cause);
            // TODO 可以在此处进行 交换机未收到的消息的具体 业务逻辑处理
        }
    }

    /**
     * 在当消息传递过程中“不可达目的地”的情况   将消息返回给生产者
     * 只有不可达目的地的时候才进行回退
     * @param returned
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("消息{}， 被交换机 {} 退回，退回原因： {}， 路由key: {}",
                returned.getMessage(), returned.getExchange(), returned.getReplyText(), returned.getRoutingKey()
                );

        // TODO 可以在此处进行回退的消息的具体 业务逻辑处理
    }
}
