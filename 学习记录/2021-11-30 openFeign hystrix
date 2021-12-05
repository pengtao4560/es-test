
openFeign
满足面向接口编程的思想

底层是 restTemplate 和 ribbon
服务调用和负载均衡

https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/#spring-cloud-openfeign
Feign是一个声明式WebService客户端。使用Feign能让编写Web Service客户端更加简单。
它的使用方法是定义一个服务接口然后在上面添加注解。Feign也支持可拔插式的编码器和解码器。Spring Cloud对Feign进行了封装，、
使其支持了Spring MVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡

由于 openFeign jar包 自己带着 ribbon, 所以设置超时时间底层就是 设置ribbon的超时时间，在yml文件中：

#设置feign客户端超时时间(OpenFeign默认支持ribbon)
ribbon:
#指的是建立连接所用的时间，适用于网络状况正常的情况下,两端连接所用的时间
  ReadTimeout: 5000
#指的是建立连接后从服务器读取到可用资源所用的时间
  ConnectTimeout: 5000


Hystrix: 已停更，但其思想设计理念非常优秀， sentinel替代 Hystrix

复杂分布式体系结构中的应用程序可能有数十个依赖关系，
每个依赖关系在某些时候将不可避免地失败。
多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，
微服务B和微服务C又调用其它的微服务，这就是所谓的“扇出”
需要一种兜底的方案，链路中断的方案

--
Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，
Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。
“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），
向调用方返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，
 这样就保证了
 服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

什么是 服务降级、服务熔断、服务限流
服务降级 fallback : 假设对方系统不可用了，给返回一个兜底的解决办法 友好的提示： 服务器忙，请稍后再试，不让客户等待并立刻返回一个友好提示

    哪些情况会降级： 程序运行异常，超时，服务熔断触发服务降级，线程池/信号量打满也会导致服务降级

服务熔断 break: 就是保险丝，跳闸限电。 达到最大访问量后，调用服务降级的方法并返回友好提示。 服务的降级->进而熔断->恢复调用链路

服务限流 flowlimit ：秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行


Hystrix 框架其实用的是tmocat的线程池

P51-P56  hsytrix 降级熔断 (兜底方案) 添加新注解 @HystrixCommand 启动类添加新注解 @EnableCircutBreaker
服务端和客户端都可以熔断降级。但是一般都放在客户端。  筷子可以加馒头也可以夹菜
1.配置文件新增：
feign:
  hystrix:
    enabled: true

2.主启动类加上@EnableHystrix

3.业务层 service类(或控制层也可以) 方法上添加注解：
@GetMapping("/consumer/payment/hystrix/timeout/{id}")
@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
})

目前痛点：
1.每个业务方法对应一个兜底的方法，代码膨胀
2.统一的和自定义的没有分开

改进：
有一个global fallback
全局的服务降级处理方法

痛点1解决：
类上加注解   @DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
再加一个全局fallback方法： payment_Global_FallbackMethod

P57 hystrix 通配服务降级 FeignClient注解 fallback属性

1.新建类实现 com.atguigu.springcloud.service.PaymentHystrixService接口
2.@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)


p58 服务熔断
    方法上添加注解：

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
熔断打开: 请求不再进行调用当前服务，内部设置时钟一般为MTTR（平均故障处理时间)，当打开时长达到所设时钟则进入半熔断状态
熔断关闭：熔断关闭不会对服务进行熔断
熔断半开：部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断


涉及断路器的三个重要参数:快照时间窗、总请求数阈值、错误百分比阈值。
1:快照时间窗:
    断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
2:请求总数阈值:
    在快照时间窗口内，必须满足请求总数阈值才有资格进行熔断。默认是20，意味着10秒，如果该hystrix命令的调用次数少于20次，
    即使所有的请求都超时或其他原因失败，断路器将不会打开。
3:错误百分比阈值:
    当请求的总数在快照时间窗内超过阈值, 比如发生了30次调用，如果在这30次调用中，有15次发生了超时异常，也就是超过了50%的错误百分比，
    在默认设定50%阈值情况下，这时候断路器就将会打开。

1.再有请求调用的时候，将不会调用主逻辑，而是直接调用降级fallback。通过断路器，实现了自动的发现错误并将错降级逻辑切换为主逻辑，减少响应延迟的效果。

2：原来的主逻辑要如何恢复呢？
对于这一问题，hystrix也为我们实现了自动恢复功能。
当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，
当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，
主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。

百度 hytrix所有的配置


P63 Hystrix图形化Dashboard搭建:
启动类上加注解 @EnableHystrixDashboard

P64 Hystrix图形化Dashboard监控实战

监控项目是9001
被监控的是 cloud-provider-hystrix-payment8001
1.启动类添加注解 @EnableCircuitBreaker
springcloud升级后留了一个坑，必须在启动类(todo尝试移动到配置类)上添加如下：

    /**
     * 此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
     * 只要在自己的项目里配置上下面的servlet就可以了
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

否则会报错： Unable to connect to Command Metric Stream.
图形化界面 url: http://localhost:9001/hystrix
文本框输入：
http://localhost:8001/hystrix.stream
