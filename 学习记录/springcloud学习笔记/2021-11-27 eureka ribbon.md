
Eureka集群远离：互相注册，相互守望


P23 eureka actuator微服务信息完善
    actuator:
    1.pom文件导入
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>
    2. 8001,8002项目yml配置文件加一个instance实例即可

    eureka:
      instance:
      #主机名称的修改
        instance-id: payment8001
      #访问路径可以显示IP地址
        prefer-ip-address: true


    3.测试 localhost:8001/actuator/health   localhost:8001/actuator/

P24 eureka服务发现Discovery
        对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息
    8001主启动类    @EnableDiscoveryClient
    相关类 8001    com.atguigu.springcloud.com.atguigu.controller.PaymentController
    *****element: cloud-payment-service
    2021-12-19 10:04:46.408  INFO 12116 --- [nio-8001-exec-1] c.a.s.com.atguigu.controller.PaymentController
          : CLOUD-PAYMENT-SERVICE	192.168.124.13	8001	http://192.168.124.13:8001
P25 Eureka自我保护理论知识
    Eureka自我保护机制：某时刻某一个服务突然不可用了，eureka不会立即清理，依旧对该微服务的信息进行保存(一段时间)
    一句话，好死不如赖活着。
    Eureka是CAP定理的    AP  可用分区一致

P26 怎么禁止自我保护
搜索类
eureka.server.enable-self-preservation
eureka服务端7001 yml配置文件添加：
eureka:
  server:
    #开启或关闭自我保护机制，保证不可用服务是否需要被及时踢除
    enable-self-preservation: true
    #开启自我保护机制，保证不可用服务被及时踢除
    eviction-interval-timer-in-ms: 90
eureka客户端8001 yml配置：
#    #Eureka客户端向服务端发送心跳的时间间隔，单位为秒(默认是30秒)
#    #lease-renewal-interval-in-seconds: 1
#    #Eureka服务端在收到最后一次心跳后等待时间上限，单位为秒(默认是90秒)，超时将剔除服务
#    #lease-expiration-duration-in-seconds: 2
搜索方法 EurekaInstanceConfigBean.setLeaseRenewalIntervalInSeconds #直译为 设置租期更新间隔


IRule 接口

ribbon 一句话概括： 负载均衡+RestTemplate调用

怎么自己实现一个负载均衡
看IRule 接口
参考： 学习记录/springcloudribbon负载均衡2021-11-27_21-58-12.png


IRule：根据特定算法中从服务列表中选取一个要访问的服务
ribbon默认的负载均衡策略：
	com.netflix.loadbalancer.RoundRobinRule 轮询
	com.netflix.loadbalancer.RandomRule     随机
	com.netflix.loadbalancer.RetryRule      先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
	WeightedResponseTimeRule                对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
	BestAvailableRule                       会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
	AvailabilityFilteringRule               先过滤掉故障实例，再选择并发较小的实例
	ZoneAvoidanceRule                       默认规则,复合判断server所在区域的性能和server的可用性选择服务器
ribbon学习：
1. github ribbon官网  https://github.com/Netflix/ribbon/wiki/Getting-Started
2. springcloud中国社区 http://docs.springcloud.cn/user-guide/ribbon/

如何替换
