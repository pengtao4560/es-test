# Java内存溢出OOM

## 经典错误

JVM中常见的两个错误

StackoverFlowError ：栈溢出

OutofMemoryError: java heap space：堆溢出



除此之外，还有以下的错误

- java.lang.StackOverflowError
- java.lang.OutOfMemoryError：java heap space
- java.lang.OutOfMemoryError：GC overhead limit exceeeded
- java.lang.OutOfMemoryError：Direct buffer memory
- java.lang.OutOfMemoryError：unable to create new native thread
- java.lang.OutOfMemoryError：Metaspace

## 架构

**OutOfMemoryError和StackOverflowError是属于Error，不是Exception**

![image-20200324144802828](images/image-20200324144802828.png)

## StackoverFlowError 

StackOverflowError：栈溢出错误，如果一个线程所需用到栈的大小>配置允许最大的栈大小，那么jvm就会抛出StackOverflow。
再深入理解一下栈的结构,栈，也叫栈内存，是jvm的内存模型之一，每当启动一个新线程的时候，jvm都会为它分配一个java栈。
jvm只会直接对java栈执行两种操作，以帧为单位的压栈和出栈。
栈的存储方式：栈内存以栈帧（Stack Frame）为单位存储，栈帧是一个内存区块，是一个有关方法和运行期数据的数据集。
当一个方法M1被调用的时候，就会产生一个栈帧S1，并被压入到栈中，M1方法又调用了M2方法，这个时候又产生栈帧S2也被压入栈，
M2方法执行完毕后，S2栈帧先出栈，S1栈帧再出栈，遵循“先进后出”原则。

### 出现StackOverflowError的原因
**一般出现这个问题是因为程序里有死循环或递归调用所产生的**。

堆栈溢出，我们有最简单的一个递归调用，就会造成堆栈溢出，也就是深度的方法调用

栈一般是512K，不断的深度调用，直到栈被撑破

```
/**
 * @author: 陌溪
 * @create: 2020-03-24-14:42
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }
    /**
     * 栈一般是512K，不断的深度调用，直到栈被撑破
     * Exception in thread "main" java.lang.StackOverflowError
     */
    private static void stackOverflowError() {
        stackOverflowError();
    }
}
```

运行结果

```
Exception in thread "main" java.lang.StackOverflowError
	at com.moxi.interview.study.oom.StackOverflowErrorDemo.stackOverflowError(StackOverflowErrorDemo.java:17)
```



## OutOfMemoryError

### java heap space

创建了很多对象，导致堆空间不够存储

```java
/**
 * Java堆内存不足
 * @see com.atguigu.review.oom81.JavaHeapSpaceDemo
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {

        // 堆空间的大小 -Xms10m -Xmx10m
        // 创建一个 80M的字节数组
        byte [] bytes = new byte[80 * 1024 * 1024];
    }
}
```

我们创建一个80M的数组，会直接出现Java heap space

```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.atguigu.review.oom81.JavaHeapSpaceDemo.main(JavaHeapSpaceDemo.java:17)
```

### GC overhead limit exceeded gc过头超过了最大极限

GC回收时间过长时会抛出OutOfMemoryError，**过长的定义是，超过了98%的时间用来做GC，并且回收了不到2%的堆内存**

连续多次GC都只回收了不到2%的极端情况下，才会抛出。假设不抛出GC overhead limit 错误会造成什么情况呢？

那就是GC清理的这点内存很快会再次被填满，迫使GC再次执行，这样就形成了恶性循环，CPU的使用率一直都是100%，而GC却没有任何成果。

![image-20200324150646260](images/image-20200324150646260.png)

代码演示：

为了更快的达到效果，我们首先需要设置JVM启动参数

```
-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
```

这个异常出现的步骤就是，我们不断的像list中插入String对象，直到启动GC回收

```java
/**
 * GC 回收超时
 * JVM参数配置: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * @author: 陌溪
 * @create: 2020-03-24-15:14
 */
public class GCOverheadLimitDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while(true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("***************i:" + i);
            e.printStackTrace();
            throw e;
        } finally {

        }

    }
}
```

运行结果

```
[Full GC (Ergonomics) [PSYoungGen: 2047K->2047K(2560K)] [ParOldGen: 7106K->7106K(7168K)] 9154K->9154K(9728K), [Metaspace: 3504K->3504K(1056768K)], 0.0311093 secs] [Times: user=0.13 sys=0.00, real=0.03 secs] 
[Full GC (Ergonomics) [PSYoungGen: 2047K->0K(2560K)] [ParOldGen: 7136K->667K(7168K)] 9184K->667K(9728K), [Metaspace: 3540K->3540K(1056768K)], 0.0058093 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 2560K, used 114K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
  eden space 2048K, 5% used [0x00000000ffd00000,0x00000000ffd1c878,0x00000000fff00000)
  from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
  to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
 ParOldGen       total 7168K, used 667K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
  object space 7168K, 9% used [0x00000000ff600000,0x00000000ff6a6ff8,0x00000000ffd00000)
 Metaspace       used 3605K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 399K, capacity 428K, committed 512K, reserved 1048576K
  
 
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	at java.lang.Integer.toString(Integer.java:403)
	at java.lang.String.valueOf(String.java:3099)
	at com.moxi.interview.study.oom.GCOverheadLimitDemo.main(GCOverheadLimitDemo.java:18)
```

我们能够看到 多次Full GC，并没有清理出空间，在多次执行GC操作后，就抛出异常 GC overhead limit



### Direct buffer memory

Netty + NIO：这是由于NIO引起的

写NIO程序的时候经常会使用ByteBuffer来读取或写入数据，这是一种基于通道(Channel) 与 缓冲区(Buffer)的I/O方式，
它可以使用Native 函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据。

ByteBuffer.allocate(capability)：第一种方式是分配JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢

ByteBuffer.allocteDirect(capability)：第二种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存的拷贝，所以速度相对较快

但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象就不会被回收，这时候怼内存充足，
但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError，那么程序就奔溃了。

一句话说：本地内存不足，但是堆内存充足的时候，就会出现这个问题
```java
package com.atguigu.review.oom81;

import sun.misc.VM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Direct buffer memory
 */
public class DirectBufferMemory {

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory(默认是电脑内存的1/4) 是：");
        System.out.println(VM.maxDirectMemory() / (double) 1024 / 1024 + "MB");
        System.out.println(VM.maxDirectMemory() / (double) 1024 / 1024 / 1024 + "G");

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        /*  -XX:MaxDirectMemorySize=5m 我们配置JM参数的 MaxDirectMemorySize 为 5m, 又占用了6m，故运行后出现了
        Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory*/
        
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}

```

我们使用 -XX:MaxDirectMemorySize=5m 配置能使用的堆外物理内存为5M

```
-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
```

然后我们申请一个6M的空间

```
// 只设置了5M的物理内存使用，但是却分配 6M的空间
ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
```

这个时候，运行就会出现问题了

```
配置的maxDirectMemory：5.0MB
[GC (System.gc()) [PSYoungGen: 2030K->488K(2560K)] 2030K->796K(9728K), 0.0008326 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC (System.gc()) [PSYoungGen: 488K->0K(2560K)] [ParOldGen: 308K->712K(7168K)] 796K->712K(9728K), [Metaspace: 3512K->3512K(1056768K)], 0.0052052 secs] [Times: user=0.09 sys=0.00, real=0.00 secs] 
Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
	at java.nio.Bits.reserveMemory(Bits.java:693)
	at java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:123)
	at java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:311)
	at com.moxi.interview.study.oom.DIrectBufferMemoryDemo.main(DIrectBufferMemoryDemo.java:19)
```



### unable to create new native thread (创建线程的上限达到了)

不能够创建更多的新的线程了，也就是说创建线程的上限达到了

在高并发场景的时候，会应用到

高并发请求服务器时，经常会出现如下异常`java.lang.OutOfMemoryError:unable to create new native thread`，
准确说该 native thread 异常与对应的平台有关

#### 导致原因：

- 应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限
- 服务器并不允许你的应用程序创建这么多线程，linux系统默认运行单个进程可以创建的线程为1024个，
  如果应用创建超过这个数量，就会报 `java.lang.OutOfMemoryError:unable to create new native thread`

#### 解决方法：

1. 想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将线程数降到最低
2. 对于有的应用，确实需要创建很多线程，远超过linux系统默认1024个线程限制，可以通过修改linux服务器配置，扩大linux默认限制

```
/**
 * 无法创建更多的线程
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("************** i = " + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

这个时候，就会出现下列的错误，线程数大概在 900多个

```
Exception in thread "main" java.lang.OutOfMemoryError: unable to cerate new native thread
```

如何查看线程数

```
ulimit -u
```
#### vim /etc/security/limits.d/90-nproc.conf

### Metaspace

元空间内存不足，Matespace元空间应用的是本地内存

`-XX:MetaspaceSize` 的处理化大小为20M

### 元空间是什么

**元空间就是我们的方法区，存放的是类模板，类信息，常量池等**

Metaspace是方法区HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟内存中，而是使用本地内存，
也即在 java8 中，class metadata（the virtual machines internal presentation of Java class），被存储在叫做
Matespace 的 native memory
JVM参数
-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m

元空间 Metaspace（java8后，java8之前是永久代）存放了以下信息：

- 虚拟机加载的类信息
- 常量池
- 静态变量
- 即时编译后的代码

模拟Metaspace空间溢出，我们不断生成类 往元空间里灌输，类占据的空间总会超过Metaspace指定的空间大小

Java8及之后的版本使Metaspace来替代永久代。
Metaspace是方法区在Hotspot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存
也即在 java8 中，classe metadata(the virtual machines internal presentation of Java class),被存储在叫做
Metaspace 的 native memory


#### 代码

在模拟异常生成时候，因为初始化的元空间为20M，因此我们使用JVM参数调整元空间的大小，为了更好的效果

```
-XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
```

代码如下：

```java
package com.atguigu.review.oom81;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 元空间溢出
 * @see com.atguigu.review.oom81.MetaspaceOutOfMemoryDemo
 */
public class MetaspaceOutOfMemoryDemo {

    static class OOMTest {

    }

    // -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
    public static void main(final String[] args) {
        // 模拟计数多少次以后发生异常
        int i = 0;
        try {
            while (true) {
                i++;
                // 使用Spring的动态字节码技术
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("发生异常的次数:" + i);
            e.printStackTrace();
        }

    }
}

```

会出现以下错误：

```
发生异常的次数: 201
java.lang.OutOfMemoryError:Metaspace
```

