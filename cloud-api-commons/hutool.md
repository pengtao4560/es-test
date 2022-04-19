类型转换工具类-Convert

类型转换工具类-Convert
痛点
在Java开发中我们要面对各种各样的类型转换问题，尤其是从命令行获取的用户参数、从HttpRequest获取的Parameter
等等，这些参数类型多种多样，我们怎么去转换他们呢？常用的办法是先整成String,然后调用XXX.parseXXX方法，还
要承受转换失败的风险，不得不加一层try catch,这个小小的过程混迹在业务代码中会显得非常难看和臃肿。

Convert类
Convert类可以说是一个工具方法类，里面封装了针对Java常见类型的转换，用于简化类型转换。Convert类中大部分方
法为toXXX,参数为Object,可以实现将任意可能的类型转换为指定类型。同时支持第二个参数defaultValue用于在转换
失败时返回一个默认值。
Java常见类型转换
```java
/*
 * 秒表封装<br>
 * 此工具用于存储一组任务的耗时时间，并一次性打印对比。<br>
 * 比如：我们可以记录多段代码耗时时间，然后一次性打印（StopWatch提供了一个prettyString()函数用于按照指定格式打印出耗时）
 *
 * <p>
 * 此工具来自：https://github.com/spring-projects/spring-framework/blob/master/spring-core/src/main/java/org/springframework/util/StopWatch.java
 *
 * <p>
 * 使用方法如下：
 *
 * <pre>
 * StopWatch stopWatch = new StopWatch("任务名称");
 *
 * // 任务1
 * stopWatch.start("任务一");
 * Thread.sleep(1000);
 * stopWatch.stop();
 *
 * // 任务2
 * stopWatch.start("任务二");
 * Thread.sleep(2000);
 * stopWatch.stop();
 *
 * // 打印出耗时
 * Console.log(stopWatch.prettyPrint());*/

```

