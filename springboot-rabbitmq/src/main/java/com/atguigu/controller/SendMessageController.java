package com.atguigu.controller;

import com.atguigu.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 发送延迟消息 生产者
 */
@RestController
@RequestMapping("/ttl/")
@Slf4j
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 开始发送消息
     *
     * @param message
     * @return
     * @see <a href="http://localhost:8672/ttl/sendMsg/xixixi">luo.com</a>
     */
    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable String message) {
        log.info("当前时间：{},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自 ttl 为 5S 的队列: " + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 10S 的队列: " + message);
        return "发送成功";
    }

    @GetMapping("/111")
    public String sendMsg2(String message) {
        return "发送成功";
    }

    /**
     * 开始发消息 TTL
     *
     * @param message 发送的消息
     * @param ttlTime 延时时长
     * @see MessagePostProcessor
     * @see <a href="http://localhost:8672/ttl/sendExpirationMsg/nihao1/20000">测试延时消息url</a>
     * @see <a href="http://localhost:8672/ttl/sendExpirationMsg/nihao2/2000">测试延时消息url</a>
     */
    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable("message") String message,
                        @PathVariable("ttlTime") String ttlTime) {
        log.info("当前时间：{}，发送一条时长 {} 毫秒TTL信息 {}给队列QC", new Date().toString(), ttlTime, message);

        rabbitTemplate.convertAndSend("X", "XC", message, (msg) -> {
            // 发送消息的时候，设置延迟时长
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
    }

    /**
     * 开始发消息基于插件的消息及延迟的时间
     * rabbitmq之基于插件的延迟队列-生产者
     */

    @GetMapping("/sendDelayMsg/(message}/(delayTime}")
    public void sendMsg(@PathVariable String message, @PathVariable Integer delayTime) {
        log.info("当前时间：{}，发送一条时长毫秒信息给延迟队列delayed.queue: {}",
                new Date().toString(), delayTime, message);
        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_QUEUE_NAME,
                // 发送消息的时候延迟时长单位：ms
                DelayedQueueConfig.DELAYED_ROUTINGKEY, message, msg -> {
                    msg.getMessageProperties().setDelay(delayTime);
                    return msg;
                });
    }
}
