
谈谈你对微服务的理解：


关键词：

    服务注册与发现  eureka(已停更) -> Zookeeper 或 Consul 或 Nacos(强烈推荐 经过百万级并发考验)
    负载均衡  ribbion /LoadBalancer (会逐渐代替ribbon)
    服务调用  feign(已不推荐使用) / OpenFeign
    服务熔断  hystrix(官网及国外已不推荐使用)/ Sentinel（强烈推荐 alibaba的）/resilence4j（国外用的很多国内用的很少）
    服务降级  hystrix
    服务网关  Zuul（X 已不推荐使用）/gateway（推荐使用）/Zuul2（胎死腹中） 
    配置中心管理 com.atguigu.springcloud.com.atguigu.config(X 已不推荐使用）/ Nacos(推荐)
    服务总线  Bus(X 已不推荐使用) / Nacos(推荐)
    服务消息队列
    服务监控
    全链路追踪
    自动化构建部署
    服务定时任务调度操作
    服务开发  springboot

1.什么是微服务：、

一种软件开发技术- ~~面向服务的体系结构（SOA）架构样式的一种变体，它提倡~~**将单一应用程序划分成一组小的服务**，
**服务之间互相协调、互相配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务与服务间采用轻量级的**
通信机制互相沟通（通常是基于HTTP的RESTful API）。每个服务都围绕着具体业务进行构建，并且能够独立地部署到生产环境、
类生产环境等。另外，应尽量避免统一的、集中式的服务管理机制，
对具体的一个服务而言，应根据上下文，选择合适的语言、工具对其进行构建。

2.SpringCloud:
  分布式微服务的一站式解决方案。是多种微服务架构落地技术的集合体
  俗称 微服务全家桶
目前已成为微服务开发的主流技术栈
springcloud官网copy： 

    Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems
    (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy,
    control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state). 
    Coordination of distributed systems leads to boiler plate patterns, and using Spring Cloud developers
    can quickly stand up services and applications that implement those patterns.
    They will work well in any distributed environment, including the developer’s own laptop,
     bare metal data centres, and managed platforms such as Cloud Foundry. 
     
    Spring Cloud为开发人员提供了一些工具来快速构建分布式系统中的一些常见模式
    (例如，配置管理、服务发现、断路器、智能路由、微代理、控制总线、一次性令牌、全局锁、领导选举、分布式会话、集群状态)。
    分布式系统的协调导致了锅炉板模式，使用Spring Cloud开发人员可以快速地支持实现这些模式的服务和应用程序。
    它们在任何分布式环境中都能很好地工作，包括开发人员自己的笔记本电脑、裸机数据中心以及像Cloud Foundry这样的托管平台。
    服务网关

[哔哩哔哩学习版本](https://www.bilibili.com/video/BV18E411x7eT?p=3&spm_id_from=pageDriver)


SpringBoot 2.X版本 和SpringCloud H版

springcloud 和 boot有严格的版本约束，必须按照
官网来查看
[springcloud官网](https://spring.io/projects/spring-cloud)
   ctrl+f 搜索：The table below outlines which version  ： 

Release train Spring Boot compatibility

        （springcloud 发布系列Spring Boot兼容性）
        cloud                   boot
        2021.0.x aka Jubilee    2.6.x
        2020.0.x aka Ilford     2.4.x, 2.5.x (Starting with 2020.0.3)
        Hoxton                  2.2.x, 2.3.x (Starting with SR5)

cloud是按照伦敦地铁站 abcd排。 目前已经 Hoxton 和 Ilford 是最新的. 
2021年12月19日09:10:29刚去看官网看最新的已是（2021.0.x aka Jubilee）
abcd e  已被过时        abcdefghij  klmn

技术选型需要有明确的依据 严格按照官网来
[springcloud官网技术选型](https://start.spring.io/actuator/info)

