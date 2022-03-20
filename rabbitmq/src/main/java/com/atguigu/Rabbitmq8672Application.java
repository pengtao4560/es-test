package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther zzyy
 * @create 2020-02-17 21:13
 */
@SpringBootApplication
@EnableEurekaClient
public class Rabbitmq8672Application {
    public static void main(String[] args) {
        SpringApplication.run(Rabbitmq8672Application.class, args);
    }
}
