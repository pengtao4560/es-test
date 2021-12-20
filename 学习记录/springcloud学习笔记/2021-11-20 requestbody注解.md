@RequestBody 注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。


restTemplate
RestTemplate提供了多种便捷访问远程Http服务的方法， 
是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集

官网地址
        https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html
使用
使用restTemplate访问restful接口非常的简单粗暴无脑。
(url, requestMap, ResponseBean.class)这三个参数分别代表 
REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。

@bean注解
Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。
(和xml配置中的bean标签的作用是一样的)

@Configuration注解

@Pathvariable注解
