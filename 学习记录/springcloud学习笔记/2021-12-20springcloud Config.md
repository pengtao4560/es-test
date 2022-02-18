分布式系统面临的配置问题：
微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。
由于每个服务都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的。

SpringCloud提供了ConfigServer来解决这个问题，我们每一个微服务自己带着一个application.yml，
上百个配置文件的管理......

是什么
SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，
配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。
怎么玩
SpringCloud Config分为服务端和客户端两部分。
服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，
加密/解密信息等访问接口
客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，
并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用git来存储配置信息，
这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。

能干嘛
	---集中管理配置文件
	---不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod/beta/release
	---运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
	---当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
	---将配置信息以REST接口的形式暴露    post、curl访问刷新均可......

推荐于git整合配置
以下来源于官网： https://spring.io/projects/spring-cloud-config

Spring Cloud Config provides server and client-side support for externalized configuration in a distributed
system. With the Config Server you have a central place to manage external properties
for applications across all environments. The concepts on both client and server map identically
to the Spring Environment and PropertySource abstractions, so they fit very well with Spring applications,
 but can be used with any application running in any language. As an application moves through
 the deployment pipeline from dev to test and into production you can manage the configuration
 between those environments and be certain that applications have everything they need to run
 when they migrate. The default implementation of the server storage backend uses git
 so it easily supports labelled versions of configuration environments,
 as well as being accessible to a wide range of tooling for managing the content.
 It is easy to add alternative implementations and plug them in with Spring configuration.

Spring Cloud Config 为分布式系统中的外部化配置提供服务器端和客户端支持。
使用 Config Server，您可以集中管理所有环境中应用程序的外部属性。客户端和服务器上的概念与 SpringEnvironment和PropertySource抽象
，因此它们非常适合 Spring 应用程序，但可以用于以任何语言运行的任何应用程序。
当应用程序通过部署管道从开发到测试再进入生产时，您可以管理这些环境之间的配置，并确保应用程序在迁移时拥有运行所需的一切。
服务器存储后端的默认实现使用 git，因此它可以轻松支持配置环境的标记版本，并且可以访问用于管理内容的各种工具。
添加替代实现并使用 Spring 配置将它们插入很容易。

 Spring Cloud Config为分布式系统中的外部化配置提供了服务器端和客户端支持。
 使用Config Server，您有一个中心位置来管理外部属性用于跨所有环境的应用程序。
 客户机和服务器映射上的概念是相同的到Spring Environment和PropertySource抽象，
 因此它们非常适合Spring应用程序，但也可以用于以任何语言运行的任何应用程序。
 当应用程序运行时从开发到测试再到生产的部署管道，您可以管理配置在这些环境之间，
 确保应用程序拥有所需的一切当他们迁移。服务器存储后端默认的实现使用git所以它很容易支持配置环境的标签版本，
 此外，还可以使用各种工具来管理内容。添加替代实现并通过Spring配置插入它们是很容易的。


curl -X POST "http://localhost:3355/actuator/refresh"

1.POM引入actuator监控
2.修改YML，暴露监控端口
3.@RefreshScope业务类Controller修改
此时修改github---> 3344 ---->3355
	http://localhost:3355/configInfo
	3355改变没有？？？
		没有，/(ㄒoㄒ)/~~
4.需要运维人员发送Post请求刷新3355
	必须是POST请求
	curl -X POST "http://localhost:3355/actuator/refresh"
5.http://localhost:3355/configInfo
-------以上----  手动版动态刷新

假如有多个微服务客户端3355/3366/3377。。。。。。
每个微服务都要执行一次post请求，手动刷新？
可否广播，一次通知，处处生效？
我们想大范围的自动刷新，求方法

所以引入了下一章：消息总线
