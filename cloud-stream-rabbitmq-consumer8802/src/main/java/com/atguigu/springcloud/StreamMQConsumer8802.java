package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class StreamMQConsumer8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMQConsumer8802.class, args);
    }

}
