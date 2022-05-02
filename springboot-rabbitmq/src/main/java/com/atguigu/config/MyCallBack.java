package com.atguigu.config;

import lombok.extern.slf4j.Slf4j;
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
public class MyCallBack implements RabbitTemplate.ConfirmCallback {

    //
    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @PostConstruct // 在其他注解都执行完成后 进行执行
    public void init() {
        // 注入
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 交换机确认会掉方法
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
        }
    }
}
