@RequestBody 注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。
-----------参考文章： [](https://www.cnblogs.com/zhuhui-site/p/10088238.html)

@requestbody的含义是在当前对象获取整个http请求的body里面的所有数据，因此spring就不可能将这个数据强制包装成Course或者List类型，
并且从@requestbody设计上来说，
只获取一次就可以拿到请求body里面的所有数据，就没必要出现有多个@requestbody出现在controller的函数的形参列表当中
当同时使用@RequestParam（）和@RequestBody时，@RequestParam（）指定的参数可以是普通元素、数组、集合、对象等等(即:当，
@RequestBody 与@RequestParam()可以同时使用时，
原SpringMVC接收参数的机制不变，只不过RequestBody 接收的是请求体里面的数据；而RequestParam接收的是key-value里面的参数
-----------

restTemplate
RestTemplate提供了多种便捷访问远程Http服务的方法， 
是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集


[官网地址](https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html)
使用
使用restTemplate访问restful接口非常的简单粗暴无脑。
(url, requestMap, ResponseBean.class)这三个参数分别代表 
REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
[]( https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html)

@bean注解
Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。
(和xml配置中的bean标签的作用是一样的)

@Configuration注解

@Pathvariable注解

SpringBoot中必须掌握的45个注解
点击关注 👉 我是程序汪 2021-06-02 13:35
来源：网络

1.SpringBoot/spring
2. Jpa
3. 全局异常处理
4. SpringCloud图片
   1.SpringBoot/spring
   @SpringBootApplication:
   包含@Configuration、@EnableAutoConfiguration、@ComponentScan通常用在主类上；

@Repository:
用于标注数据访问组件，即DAO组件；

@Service:
用于标注业务层组件；

@RestController:
用于标注控制层组件(如struts中的action)，包含@Controller和@ResponseBody；

@Controller:
用于标注是控制层组件，需要返回页面时请用@Controller而不是@RestController；

@Component:
泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注；

@ResponseBody:
表示该方法的返回结果直接写入HTTP response body中，一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，

加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中；比如异步获取json数据，加上@responsebody后，会直接返回json数据；

@RequestBody:
参数前加上这个注解之后，认为该参数必填。表示接受json字符串转为对象 List等；

@ComponentScan:
组件扫描。个人理解相当于，如果扫描到有@Component @Controller @Service等这些注解的类，则把这些类注册为bean*；

@Configuration:
指出该类是 Bean 配置的信息源，相当于XML中的，一般加在主类上；

@Bean:
相当于XML中的,放在方法的上面，而不是类，意思是产生一个bean,并交给spring管理；
         Spring Bean是被实例的，组装的及被Spring 容器管理的Java对象
@EnableAutoConfiguration:
让 Spring Boot 根据应用所声明的依赖来对 Spring 框架进行自动配置，一般加在主类上；

@AutoWired:
byType方式。把配置好的Bean拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作；

当加上（required=false）时，就算找不到bean也不报错；

@Qualifier:
当有多个同一类型的Bean时，可以用@Qualifier(“name”)来指定。与@Autowired配合使用；

@Resource(name=”name”,type=”type”)：
没有括号内内容的话，默认byName。与@Autowired干类似的事；

@RequestMapping:
RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径；

   该注解有六个属性:
   
   params:指定request中必须包含某些参数值是，才让该方法处理。
   
   headers:指定request中必须包含某些指定的header值，才能让该方法处理请求。
   
   value:指定请求的实际地址，指定的地址可以是URI Template 模式
   
   method:指定请求的method类型， GET、POST、PUT、DELETE等
   
   consumes:指定处理请求的提交内容类型（Content-Type），如application/json,text/html;
   
   produces:指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回。

@GetMapping、@PostMapping等:
相当于@RequestMapping（value=”/”，method=RequestMethod.Get\Post\Put\Delete等） 。是个组合注解；

@RequestParam:
用在方法的参数前面。相当于 request.getParameter()；

@PathVariable:
路径变量。如 RequestMapping(“user/get/mac/{macAddress}”) ；

public String getByMacAddress(@PathVariable(“macAddress”) String macAddress) {
//do something;
}
参数与大括号里的名字相同的话，注解后括号里的内容可以不填。

2. Jpa
   @Entity:
   @Table(name=”“):
   表明这是一个实体类。一般用于jpa ，这两个注解一般一块使用，但是如果表名和实体类名相同的话，@Table可以省略；

@MappedSuperClass:
用在确定是父类的entity上。父类的属性子类可以继承；

@NoRepositoryBean:
一般用作父类的repository，有这个注解，spring不会去实例化该repository；

@Column:
如果字段名与列名相同，则可以省略；

@Id:
表示该属性为主键；

@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = “repair_seq”):
表示主键生成策略是sequence（可以为Auto、IDENTITY、native等，Auto表示可在多个数据库间切换），指定sequence的名字是repair_seq；

@SequenceGenerator(name = “repair_seq”, sequenceName = “seq_repair”, allocationSize = 1):
name为sequence的名称，以便使用，sequenceName为数据库的sequence名称，两个名称可以一致；

@Transient:
表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.

如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient,否则,ORM框架默认其注解为@Basic；

@Basic(fetch=FetchType.LAZY):
标记可以指定实体属性的加载方式；

@JsonIgnore:
作用是json序列化时将java bean中的一些属性忽略掉,序列化和反序列化都受影响；

@JoinColumn(name=”loginId”):
一对一：本表中指向另一个表的外键。

一对多：另一个表指向本表的外键。

@OneToOne
@OneToMany
@ManyToOne:
对应Hibernate配置文件中的一对一，一对多，多对一。

3. 全局异常处理
   @ControllerAdvice:
   包含@Component。可以被扫描到。统一处理异常；

@ExceptionHandler(Exception.class):
用在方法上面表示遇到这个异常就执行以下方法。

4. SpringCloud
   @EnableEurekaServer:
   用在springboot启动类上，表示这是一个eureka服务注册中心；

@EnableDiscoveryClient:
用在springboot启动类上，表示这是一个服务，可以被注册中心找到；

@LoadBalanced:
开启负载均衡能力；

@EnableCircuitBreaker:
用在启动类上，开启断路器功能；

@HystrixCommand(fallbackMethod=”backMethod”):
用在方法上，fallbackMethod指定断路回调方法；

@EnableConfigServer:
用在启动类上，表示这是一个配置中心，开启Config Server；

@EnableZuulProxy:
开启zuul路由，用在启动类上；

@SpringCloudApplication:
包含

@SpringBootApplication

@EnableDiscovertyClient

@EnableCircuitBreaker

分别是SpringBoot注解、注册服务中心Eureka注解、断路器注解。对于SpringCloud来说，这是每一微服务必须应有的三个注解，所以才推出了@SpringCloudApplication这一注解集合。## *1.SpringBoot/spring *
