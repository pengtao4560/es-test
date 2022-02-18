为什么引入cloud Stream
what
解决的痛点？

MQ 消息中间件
ActiveMQ
RabbitMQ
RocketMQ
Kafka

可能在同一种业务流程中，存在两种MQ
RabbitMQ 流转到 下一个业务流程变成了  Kafka
由此导致的
    1.切换
    2.维护
    3.开发
    成本，又说没有一种新的技术诞生，让我们不再关注具体MQ的细节
    我们只需要一种适配绑定的方式，自动的给我们在各种MQ内切换

什么是SpringCloudStream
官方定义 Spring Cloud Stream 是一个构建消息驱动微服务的框架。

应用程序通过 inputs 或者 outputs 来与 Spring Cloud Stream中binder对象交互。
通过我们配置来binding(绑定) ，而 Spring Cloud Stream 的 binder对象负责与消息中间件交互。
所以，我们只需要搞清楚如何与 Spring Cloud Stream 交互就可以方便使用消息驱动的方式。

通过使用Spring Integration （整合） 来连接消息代理中间件以实现消息事件驱动。
Spring Cloud Stream 为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布-订阅、消费组、分区的三个核心概念。

目前仅支持RabbitMQ、Kafka。

屏蔽底层消息中间件的差异,降低切换成本，统一消息的编程模型
Spring Cloud Stream is a framework for building highly scalable event-driven microservices
connected with shared messaging systems.

The framework provides a flexible programming model built on already established and familiar Spring idioms
and best practices, including support for persistent pub/sub semantics, consumer groups, and stateful partitions.
Spring Cloud Stream是一个用于构建高度可伸缩的事件驱动微服务的框架连接到共享的消息传递系统该框架提供了一个灵活的编程模型，
建立在已经建立和熟悉的Spring习惯用法和最佳实践之上，
包括对持久发布/订阅语义、消费者组和有状态分区的支持
Binder Implementations 绑定实现

绑定器的设计思想：
在没有绑定器这个概念的情况下，我们的SpringBoot应用要直接与消息中间件进行信息交互的时候，
由于各消息中间件构建的初衷不同，它们的实现细节上会有较大的差异性
通过定义绑定器作为中间层，完美地实现了应用程序与消息中间件细节之间的隔离。
通过向应用程序暴露统一的Channel通道，使得应用程序不需要再考虑各种不同的消息中间件实现。

 参考思维导图：

通过定义绑定器Binder作为中间层，实现了应用程序与消息中间件细节之间的隔离。

设计思想
	标准MQ
		生产者/消费者之间靠消息媒介传递信息内容
			Message
		消息必须走特定的通道
			消息通道MessageChannel
		消息通道里的消息如何被消费呢，谁负责收发处理
			消息通道MessageChannel的子接口SubscribableChannel，由MessageHandler消息处理器所订阅
	为什么用Cloud Stream
		stream凭什么可以统一底层差异？
		Binder
			INPUT对应于消费者
			OUTPUT对应于生产者
	Stream中的消息通信方式遵循了发布-订阅模式
		Topic主题进行广播
			在RabbitMQ就是Exchange
			在Kakfa中就是Topic

Spring Cloud Stream标准流程套路
	Binder
		绑定器 很方便的连接中间件，屏蔽差异
	Channel
		通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过Channel对队列进行配置
	Source和Sink 简单理解就是  输入和输出
		简单的可理解为参照对象是Spring Cloud Stream自身，
    从Stream发布消息就是输出，接受消息就是输入。

运行后有两个问题
	有重复消费问题
	消息持久化问题

	比如在如下场景中，订单系统我们做集群部署，都会从RabbitMQ中获取订单信息，
    那如果一个订单同时被两个服务获取到，那么就会造成数据错误，我们得避免这种情况。
    这时我们就可以使用Stream中的消息分组来解决
    ￼
    注意在Stream中处于同一个group中的多个消费者是竞争关系，就能够保证消息只会被其中一个应用消费一次。
    不同组是可以全面消费的(重复消费)，
    同一组内会发生竞争关系，只有其中一个可以消费。

    不同组是可以全面消费的（重复消费）
    同一组内会发生竞争关系，只有其中一个可以消费
    流水号默认是每一个都是不同的组 需要解决重复消费问题
Queue studyExchange.anonymous.XwpTDsYTSIWU_3GJR21O-g
Queue studyExchange.anonymous.d-XJGUc_QeiNInzYhRQ-8w
故障现象：重复消费
导致原因：默认分组group是不同的，组流水号不一样，被认为不同组，可以消费。

自定义配置分组，
自定义配置分为同一个组，解决重复消费问题

￼
分布式微服务应用为了实现高可用和负载均衡，实际上都会部署多个实例，本例阳哥启动了两个消费微服务(8802/8803)
多数情况，生产者发送消息给某个具体微服务时只希望被消费一次，按照上面我们启动两个应用的例子，虽然它们同属一个应用，
但是这个消息出现了被重复消费两次的情况。为了解决这个问题，在Spring Cloud Stream中提供了消费组的概念。

持久化
	通过上述，解决了重复消费问题，再看看持久化
	停止8802/8803并去除掉8802的分组group: atguiguA
		8803的分组group: atguiguA没有去掉
	8801先发送4条消息到rabbitmq
	先启动8802，无分组属性配置，后台没有打出来消息
	再启动8803，有分组属性配置，后台打出来了MQ上的消息
