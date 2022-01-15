package com.springcloud.atguigu.cloudalibabaproviderpayment9001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayment9001Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosPayment9001Application.class, args);
    }

}
