package 海尔2019年笔试题;

import java.io.Serializable;

/**
 *
 */
public class Test {

    /**
     1. 以下对接口描述不正确的是（D）
     A 接口没有提供构造方法
     B 接口中可以有具体方法
     C 接口中的属性默认使用public static final修饰
     D 接口不允许多继承
     */

    /**
     2.下拉叙述正确的是 B
     A. abstract 修饰符可修饰字段、方法和类
     B. 抽象类可以有非抽象方法
     C. 抽象方法可以定义具体实现
     D. 抽象类可以实例化
     */
    /**
     3.下列对象中是线程安全的是 D
     A LinkedList
     B HashMap
     C HashSet
     D StringBuffer
     */

    /**
     4.在Java Web开发中，如果某个数据需要跨多个请求存在，则数据应该存储在（A）中
     A session
     B page
     C request
     D response
     */
    /**
     5.栈初始状态为空，a.b.c,d.e.f依次入栈，出栈顺序为b,d.c.f.e,a,则栈的容量至少为 B 3
     A 2
     B 3
     C 4
     D 6
     【解析】根据条件，可做如下操作：①a、b进栈，栈中有a和b两个元素；②b出栈，c、d进栈，栈中有a、c、d
     这3个元素；③d、c出栈，e、f进栈，栈中有a、e、f这3个元素；④元素f、e、a出栈，栈为空。由此可见，
     进栈顺序为a、b、c、d、e、f,出栈顺序为b、d、c、f、e、a,满足题目要求。每次进栈操作后，栈中最多
     有3个元素，所以，为了顺利完成这些操作，栈的容量应至少为3。
     */
    /**
     6. 0.6332的数据类型是（B）
     A float
     B double
     C Float
     D Double
     */
    @org.junit.Test
    public void test6() {
        double a = 0.6332;
    }

    /**
     * 7. 阅读代码：
     * var str = "abc123def".
     * console.log(str.replace(/\d+/gi,"*"));
     * 以上代码的执行结果是（C） 浏览器F12 console中 copy两句话得到结果
     * A abc***def
     * B abc***def***
     * C abc*def
     * D abc*def*
     */
    /**
     8. 使用SQL命令将教师表teacher中工资salary字段的值增加500，应该使用的命令是（D）
     A replace teacher salary with salary+500
     B update teacher salary with salary+500
     C update teacher set salary with salary+500
     D update teacher set salary=salary+500
     */

    /**
     9.如何找到表单中隐藏的div？D
     A、$("div:none") B、$("div:invisiable") C、$("div:even")
     D、$("div:hidden")
     */

    /**
     10. 阅读代码
     public class Test{
     public static void main(String[] args) {
     String s;
     System.out.println("s="+s);
     }
     }
     以上代码的执行结果是（C）
     A 代码得到编译，并输出"s="
     B 代码得到编译，并输出"s=null"
     C 代码不能编译通过
     D 代码得到编译，但捕获到 NullPointException 异常
     */
    @org.junit.Test
    public void test10() {
        String s;
        // System.out.println("s=" + s); 编译不通过
    }

    @org.junit.Test
    public void test11() {
        int i1=3,i2=4;
        System.out.println(divide(i1,i2));
    }

    private static double divide(int i1,int i2) {
        int i = i1 / i2;
        System.out.println("divide: " + i);
        return i1/i2;
    }

    @org.junit.Test
    public void test12() {
        Thread t = new Thread() {
            public void run() {
                System.out.print("pong");
            }
        };
        t.run();
        System.out.print("ping");
        // 输出 pongping
        // 如果是 t.start()，输出 pingpong
    }

    public static void main(String[] args) {
        Integer i=9999;
        increment(i);
        System.out.println(i);
    }
    public static void increment(Integer i) {
        i=i+1;
    }

    @org.junit.Test
    public void test14() {
        Integer c=1000;
        Integer d=1000;
        System.out.println(c==d);

        double e=3*0.3;
        double f=0.9;
        System.out.println(e==f);
    }
    /*
    15. 简述运行时异常(Runtime Exception)与受检异常(Checked Exception)的主要区别。列出2个常见的运行时异常。
    1.运行时异常是RuntimeException类及其子类，是非检查性异常，如空指针异常、数组下标越界异常、类型转换异常、算术异常。
    运行时异常与检查性异常最大的区别在于运行时异常不用对其捕获，JVM会自行处理，
    会自动catch运行时异常并停止线程，打印异常。
    如果产生运行时异常，相当于bug了，则需要修改代码避免异常，当然也可以手动抛出或者捕捉异常。

    2.检查性异常是Exception类本身及其子类中除运行时异常之外的其他异常，检查性异常必须通过throws进行申明抛出，
    或者通过try-catch进行捕捉处理，否则不能通过编译，如IO异常，SQL异常等。
    - NullPointerException （空指针异常）
    - ClassCastException （类转换异常）
    - ArithmeticException（算术异常）
    - IllegalArgumentException （非法参数异常）
    - IndexOutOfBoundsException （下标越界异常）
    - SecurityException （安全异常）



16. 简述你对泛型的理解，并解释PECS原则。

在泛型之前，你必须转换从集合中读取的每个对象。如果有
人不小心插入了错误类型的对象，则在运行时可能会失败。
使用泛型，可以告诉编译器在每个集合中允许哪些类型的对象。编译器会自动插入强制转换
泛型类型信息在运行时被删除

PECS即 Producer extends Consumer super
即如果参数化类型表示一个T的生产者，使用<? extends T>，
如果表示一个T的消费者，使用<? super T>。
再通俗点说：

从集合中取元素, 使用<? extends T>通配符
向集合中放元素, 使用<? super T>通配符
对于PE：放东西进去，无法确认是哪个子类，但取东西时可以返回父类。有上界。上界<? extends T>不能往里存，
只能往外取。频繁往外读取内容的(生产内容）,适合用上界Extends。
对于CS：取东西出来，无法判定是哪个父类，但放东西是可以知道子类。有下界。
下界<? super T>不影响往里存，但往外取只能放在Object对象里。 经常往里插入的（消费内容）,适合用下界Super。


17. 简述你对ThreadLocal的理解，并举例一个ThreadLocal的应用场景。
通常情况下，变量是可以被任何一个线程访问并修改的。**如果想实现每一个线程都有自己的专属本地变量该如何解决呢？
** JDK 中提供的`ThreadLocal`类可以实现每一个线程都有自己的专属本地变量。
**`ThreadLocal`类主要解决的就是让每个线程绑定自己的值，
可以将`ThreadLocal`类形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据。**

**如果你创建了一个`ThreadLocal`变量，那么访问这个变量的每个线程都会有这个变量的本地副本，
这也是`ThreadLocal`变量名的由来。他们可以使用 `get（）` 和 `set（）` 方法来获取默认值或将其值更改
为当前线程所存的副本的值，
从而避免了线程安全问题。**

**最终的变量是放在了当前线程的 `ThreadLocalMap` 中，并不是存在 `ThreadLocal` 上，`ThreadLocal` 可以理解为
只是`ThreadLocalMap`的封装，传递了变量值。** `ThrealLocal` 类中可以通过`Thread.currentThread()`获取到
当前线程对象后，直接通过`getMap(Thread t)`可以访问到该线程的`ThreadLocalMap`对象。

**每个`Thread`中都具备一个`ThreadLocalMap`，而`ThreadLocalMap`可以存储以`ThreadLocal`为 key ，
Object 对象为 value 的键值对。**

再举个简单的例子：

比如有两个人去宝屋收集宝物，这两个共用一个袋子的话肯定会产生争执，但是给他们两个人每个人分配一个袋子的话
就不会出现这样的问题。
如果把这两个人比作线程的话，那么 ThreadLocal 就是用来避免这两个线程竞争的。

全局存储用户信息在现在的系统设计中，前后端分离已基本成为常态，分离之后如何获取用户信息就成了一件麻烦事，
通常在用户登录后， 用户信息会保存在Session或者Token中。这个时候，
我们如果使用常规的手段去获取用户信息会很费劲，
拿Session来说，我们要在接口参数中加上HttpServletRequest对象，然后调用 getSession方法，
且每一个需要用户信息的接口都要加上这个参数，才能获取Session，这样实现就很麻烦了。
在实际的系统设计中，我们肯定不会采用上面所说的这种方式，而是使用ThreadLocal，
我们会选择在拦截器的业务中， 获取到保存的用户信息，然后存入ThreadLocal，
那么当前线程在任何地方如果需要拿到用户信息都可以使用ThreadLocal的get()方法
 (异步程序中ThreadLocal是不可靠的)
 对于笔者而言，这个场景使用的比较多，当用户登录后，
 会将用户信息存入Token中返回前端，当用户调用需要授权的接口时，需要在header中携带 Token，
 然后拦截器中解析Token，获取用户信息，调用自定义的类(AuthNHolder)存 ThreadLocal中，
 当请求结束的时候，将ThreadLocal存储数据清空，
 中间的过程无需在关注如何获取用户信息，只需要使用工具类的get方法即可。

18. 简述SQL注入漏洞的概念，以及如何避免被SQL注入攻击。
SQL注入（SQLi）是一种注入攻击，，可以执行恶意SQL语句。它通过将任意SQL代码插入数据库查询，
使攻击者能够完全控制Web应用程序后面的数据库服务器。攻击者可以使用SQL注入漏洞绕过应用程序安全措施；
可以绕过网页或Web应用程序的身份验证和授权，并检索整个SQL数据库的内容；
还可以使用SQL注入来添加，修改和删除数据库中的记录。

1、数据校验。使用正则表达式等方法对输入参数进行校验，采用SQL预编译 PreparedStatement，它有内置的
防止SQL注入的措施

2. 普通用户与系统管理员用户的权限要有严格的区分
请记住永远不要使用超级用户或所有者帐号去连接数据库！当数据库被攻击时将损伤限制在当前表的范围

3. 当数据库操作失败的时候，尽量不要将原始错误日志返回，把代码里的SQL语句暴露出来，
 以防止攻击者利用这些错误信息进行SQL注入

4. 强迫使用参数化语句
如果在编写SQL语句的时候，用户输入的变量不是直接嵌入到SQL语句，而是通过参数来传递这个变量的话，
就可以有效的防治SQL注入式攻击

5.对数据库的敏感信息进行加密， 对数据库进行备份

19. 给定一个Foo的列表，请编写一个方法，将Foo加工为树形结构(即填满children属性)
class Foo {
Long id;
Long parentId;
List<Foo> children;
}
public Foo buildTree(List<Foo> fooList) {
Foo root=new Foo();

return root;
}
    */
/**
 public List<Foo> generateFoo(List<Foo> FooList) {
 List<Foo> rootFoo = new ArrayList<>();

 for (Foo Foo : FooList) {
     // 第一步 筛选出最顶级的父节点
     if (0 == Foo.getParentId()) {
     rootFoo.add(Foo);
 }
 // 第二步 筛选出该父节点下的所有子节点列表
 for (Foo node : FooList) {
      if (node.getParentId().equals(Foo.getId())) {
         if (CollectionUtils.isEmpty(Foo.getChildren())) {
         Foo.setChildren(new ArrayList<>());
         }
        Foo.getChildren().add(node);
        }
    }
 }
 return rootFoo;
}
 */

}
abstract class AbstractClass {
    // abstract String s = "'"; //Modifier 'abstract' not allowed here
    public int m1(){
        return 1;
    }
}
interface TestInterface extends Cloneable, Serializable {
    String s = null;
    default void test(){
    }
}
