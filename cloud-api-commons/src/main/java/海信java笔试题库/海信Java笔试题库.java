package 海信java笔试题库;

import com.beust.ah.A;
import org.junit.Test;

/**
 * 海信java
 */
public  class 海信Java笔试题库 {

    @Test
    public void test1() {
    /*
    1.以下哪种手段不可以防御CSRF漏洞？选 D
    A 验证HTTP Referer字段
    B 使用验证码
    C.在请求地址中添加token并验证
    D.     D 过滤用户输入的内容*/
    }

    @Test
    public void test2() {
        /**
         * 2..在java中下列关于自动类型转换说法正确的是  A
         * A. 基本数据类型和String相加结果一定是字符串型
         * B. char类型和int类型相加结果一定是字符
         * C. double类型可以自动转换为int
         * D. char+int+double+"结来一定是double:
         */
    }

/*    3.下列哪一种叙述是正确的: D
    A abstract 修饰符可修饰字段、方法和类   PS: abstract不可以修饰字段
    B.抽象方法的 body 部分必须用一对大括号{} 包住
    C.声明抽象方法，大搭号可有可无
    D.声明抽忽方法不可写出大括号
    */

    // public abstract void m1();
    // public abstract String s1; // Modifier 'abstract' not allowed here

    @Test
    public void test4() {
        System.out.println(foo(5));
        // 5
    }
    int foo(int n){
        if (n < 2) return n;
        return foo(n-1) + foo(n-2);
    }

    /**
     5,下列思种说法是正确的 D
     实例方法可直接调用超类的实例方法
     实例方法可直接调用超类的类方法
     实例方法可百接润用其他类的实列方法
     实例方法可直接调用本类的类方法
     */
    static int m1() {
        return 1;
    }
    /**
     6.你要在 Oracle 中定义 SQL 查询。下列哪个数据库对象不能直接从 select 语句中引用？ C 索引
     A 表 B 序列 c 索引 D 视图
     */

    /**
     7.关于feign,以下说法错误的是
     A.feign采用的是基于接口的注解
     B.整合了Hystrix,具有熔断的能力
     定义一个接口@FeignClient(name-“xox) 指定调用哪个服务
     D feign 没有集成 ribbon
     8.下列语句与事务控制无关的是 D SAVEBACK与事务无关
     A. COMMIT
     B. SAVEPOINT
     c. ROLLBACK
     D. SAVEBACK

     9.Java语言中，while 和 do... while 循环的主要区别是（B）
     A. while 的循环控制条件比 do... while 的循环控制条件严格
     B. do... while 的循环体至少无条件执行一次
     C. do... while 允许从外部转到循环体内
     D. do... while 的循环体不能是复合语句
     */

    /**
     10.设a=3,则表达式(-a)<<a 的结果是
     A. 16 B.8 C. 24 D. 12
     */
    @Test
    public void test10() {
        int a  = 3;
        int i = (--a) << a;
        System.out.println(i);
    }

    @Test
    public void test11() {
        long a = Math.round(-1.5);
        System.out.println(a);  // -1
    }

    /**
     12.下列师些语句关于内存回收的说法是正确的 (B)
     A 程序员必须创建一个线程来存放内存
     B 内存回收程序负责释放无用内存
     C.内存百收程序允许程序员直接释放内存
     D. 内存回收程序可以在指定的时间内释放内存对象
     */

    /**\
     以下结构中，插入性能最高的是 B
     A. ArrayList
     B. LinkedList
     C. for
     D.
     */

    /**
     以下能够删除一列的是： B
     A. alter table emp remove ename
     B. ater table emp drop column ename
     C. alter table emp delete column ename
     D) alter table emp delete ename
     */

    /**
     * Feign继承了 Ribbon具有远程调用
     *
     * Feign 实现服务调用是通过默认集成哪个组件实现的  B
     * A. gateway
     * B. ribbon
     * C. eureka
     * D. zuul
     */

    /**
     22.Effective Java中提到使用“单元素的枚举类型已经成为实现单例的最佳方法"，对此以下说法正确的是：多选：A
     A. 枚举单例是线程安全的
     B. 枚举单例具备延迟加载特性
     C. 枚举单例可以预防通过反射机制创建多个实例
     D. 枚举单例可以预防通过反序列化创建多个实例
     E. 枚举单例只允许创建自身的单例
     */
    public void example(){}
    /**
     23.下面哪个函数是 public void example(){}的重载函数？（）多进题3.0分
     答案：AD
     A. private void example (int m){}
     B. public int example(){}
     C. public void example2(){}
     D. public int example (int m, float f){}
     */
    private void example (int m){}
    //public int example(){return 1;}
    public int example (int m, float f){return 1;}

    /**
     25.下列属于关系型数据库的是（AB）
     A. Oracle
     B. MySql
     C. IMS
     D. MongoDB
     */
    /**
     26.List、Set、Map 哪个继承自 Collection 接口，一下说法正确的是（C）多选题3.0分
     A. List
     B. Set Map
     C. List Set
     D. List Map Set
     */
}
