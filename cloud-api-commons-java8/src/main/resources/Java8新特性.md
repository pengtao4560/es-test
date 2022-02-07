_[java8新特性](Java8%20新特性.pdf)

TODO Consumer

### Lambda 表达式

```jshelllanguage
    import java8.lambda.TestLambda;
    import java8.lambda.TestLambda2;
    class test {/**{@link TestLambda} */
    }
    class test {/**{@link TestLambda2} */
    }
```

     一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
                                箭头操作符将 Lambda 表达式拆分成两部分：
    
     左侧：Lambda 表达式的参数列表
     右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
    
     语法格式一：无参数，无返回值
            () -> System.out.println("Hello Lambda!");
    
     语法格式二：有一个参数，并且无返回值
            (x) -> System.out.println(x)
    
     语法格式三：若只有一个参数，小括号可以省略不写
            x -> System.out.println(x)
    
     语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
            Comparator<Integer> com = (x, y) -> {
                System.out.println("函数式接口");
                return Integer.compare(x, y);
            };
    
     语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
            Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    
     语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
            (Integer x, Integer y) -> Integer.compare(x, y);
    
     上联：左右遇一括号省
     下联：左侧推断类型省
     横批：能省则省
    
     二、Lambda 表达式需要“函数式接口”的支持
     函数式接口：接口中有且仅有一个抽象方法(但是可以有多个非抽象方法的接口)称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
                 可以检查是否是函数式接口
练习参考：
```jshelllanguage
    class test {/** {@link java8.lambda.TestLambda3} */
    }
```
但是现在应用lambda表达式经常需要使用 自己创建函数式接口。
而实际上 java8已经把常用的函数式接口写好
### 函数式接口
    java8及以后 内置四大核心函数式接口：
    消费型接口(有去无回happy消费)：Comsumer<T>
        void accept(T t)
    供给型接口(无去有回供给ji)：Supplier<T>
        T get();
    函数式接口(T参数R返回值)：Function<T, R>
        R apply(T t);
    断言型接口: Predicate<T>
        boolean: test(T t);
[...](其他扩展函数式接口.png) 或[百度函数式接口](https://www.baidu.com/s?ie=utf-8&wd=%E5%87%BD%E6%95%B0%E5%BC%8F%E6%8E%A5%E5%8F%A3)
或[菜鸟教程Java 8 函数式接口](https://www.runoob.com/java/java8-functional-interfaces.html)

```jshelllanguage
    class test {/** {@link java8.lambda.TestLambda5}*/
}
```    

###方法引用与构造器引用
##方法引用：
若 Lambda体中的内容有方法已经实现了，
我们可以使用“方法引用”（方法引用可以理解为是Lambda表达式的另外一种表达形式）
主要有三种语法格式：
    
    对象::实例方法名
    类::静态方法名
    类::实例方法名
        
注意：

    1. Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中 抽象方法的函数列表和返回值类型保持一致！
    2. 若Lambda 参数列表中的第一个参数时实例方法的调用者，而第二个参数时实例方法的参数时，可以使用
     ClassName::Method 的形式

### 构造器引用
注意：

    需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致

### 三、数组引用
方法引用、构造器引用、数组引用参考类方法：
```jshelllanguage
    class test {/** {@link java8.lambda.TestMethodRef}*/}
```

### Stream API
    概念：
    Stream 是 Java8 中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，
    可以执行非常复杂的查找、过滤和映射数据等操作。
    使用Stream API 对集合数据进行操作，就类似于使用 SQL 执行的数据库查询。
    也可以使用 Stream API 来并行执行操作。简而言之，Stream API 提供了一种高效且易于使用的处理数据的方式。

流(Stream) 到底是什么呢？ 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
“集合讲的是数据，流讲的是计算！”
注意：

    ①Stream 自己不会存储元素。
    ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。 
    ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

                              一系列流水线的中间操作                                                
    数据源(集合、数组等) ------------------------------- 产生一个新流
    原来的数据源不会受到影响

Stream 的操作三个步骤
 创建 Stream
一个数据源（如：集合、数组），获取一个流
 中间操作
一个中间操作链，对数据源的数据进行处理(多个中间操作构成一个流水线)
 终止操作(终端操作)
一个终止操作，执行中间操作链(称为“惰性求值”)，并产生结果

代码参考
```jshelllanguage
    class test {/** {@link java8.stream.TestStreamaAPI}*/}
    class test {/** {@link java8.stream.StreamAPITest}*/}
```
学习参考尚硅谷java8 以及：
[Stream API 学习博客](https://blog.csdn.net/pan_junbiao/article/details/105913518)

### Stream的终止操作---收集
Collector 接口中方法的实现决定了如何对流执行收集操作(如收 集到 List、Set、Map)。
但是 Collectors 实用类提供了很多静态 方法，可以方便地创建常见收集器实例，


















### LocalDate、LocalTime、LocalDateTime 类

LocalDate、LocalTime、LocalDateTime 类的实例是不可变的对象 分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。 它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。
注：ISO-8601日历系统是国际标准化组织制定的现代公民的日期和时间的表示法

LocalDate 使用 ISO-8601日历系统的日期 LocalTime 使用 ISO-8601日历系统的时间 LocalDateTime 使用 ISO-8601日历系统的日期和时间

    方法                                                  用法                                           代码

    now()                                               静态方法，根据当前时间创建对象 
                                                                                                        LocalDate localDate = LocalDate.now();
                                                                                                        LocalTime localTime = LocalTime.now();
                                                                                                        LocalDateTime localDateTime = LocalDateTime.now();
    of()                                                 静态方法，根据指定日期/时间创建对象
                                                                                                        LocalDate localDate = LocalDate.of(2016, 10, 26);
                                                                                                        LocalTime localTime = LocalTime.of(02, 22, 56);
                                                                                                        LocalDateTime localDateTime = LocalDateTime.of(2016, 10,
                                                                                                        26, 12, 10, 55);
    plusDays, plusWeeks, plusMonths, plusYears
    向                                                   当前 LocalDate 对象添加几天、几周、几个月、几年

    minusDays, minusWeeks, minusMonths, minusYears      从当前 LocalDate 对象减去几天、几周、几个月、几年

    plus, minus                                         添加或减少一个 Duration 或 Period

    withDayOfMonth, withDayOfYear, withMonth, withYear  将月份天数、年份天数、月份、年 份 修 改 为 指 定 的 值 并 返 回 新 的 LocalDate 对象

    getDayOfMonth                                       获得月份天数(1-31)

    getDayOfYear                                        获得年份天数(1-366)

    getDayOfWeek                                        获得星期几(返回一个 DayOfWeek 枚举值)

    getMonth                                            获得月份, 返回一个 Month 枚举值

    getMonthValue                                       获得月份(1-12)

    getYear                                             获得年份

    until                                               获得两个日期之间的 Period 对象，或者指定 ChronoUnits 的数字

    isBefore, isAfter                                   比较两个 LocalDate

    isLeapYear                                          判断是否是闰年

### Duration 和 Period

Duration:用于计算两个“时间”间隔 Period:用于计算两个“日期”间隔

    类                               To 遗留类                               From 遗留类
    java.time.Instant
    java.util.Date                  Date.from(instant)                      date.toInstant()
        
    java.time.Instant
    java.sql.Timestamp              Timestamp.from(instant)                 timestamp.toInstant()
    
    java.time.ZonedDateTime
    java.util.GregorianCalendar
                                    GregorianCalendar.from(zonedDateTime)   cal.toZonedDateTime()
    java.time.LocalDate
    java.sql.Time                   Date.valueOf(localDate)                 date.toLocalDate()
    java.time.LocalTime
    java.sql.Time                   Date.valueOf(localDate)                 date.toLocalTime()
    java.time.LocalDateTime
    java.sql.Timestamp              Timestamp.valueOf(localDateTime)        timestamp.toLocalDateTime()
    java.time.ZoneId
    java.util.TimeZone              Timezone.getTimeZone(id)                timeZone.toZoneId()
    java.time.format.DateTimeFormatter
    java.text.DateFormat            formatter.toFormat()                    无

// TODO 测试类进行测试以上转换
```jshelllanguage
    class test {/**{@link java8.localdate.LocalDateAndLocalTimeAndLocalDateTime}*/}
    class test {/**{@link java8.localdate.LocalDateTest}*/}
```

### Optional 类
Optional 类 Optional<T> 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在， 
原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。并且 可以避免空指针异常。
常用方法： Optional.of(T t) : 创建一个 Optional 实例 

    Optional.empty() : 创建一个空的 Optional 实例 
    Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
    isPresent() : 判断是否包含值 orElse(T t) : 如果调用对象包含值，返回该值，否则返回 t 
    orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值 
    map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
    flatMap(Function mapper):与 map 类似，要求返回值必须是Optional

```jshelllanguage
    class test {/** {@link java8.optional.TestOptional}*/}
```
### 重复注解与类型注解
