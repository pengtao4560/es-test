
从22章节开始进行备注，以前章节 待补全

### 22.【接口仅用来定义类型】USE INTERFACES ONLY TO DEFINE TYPES
常量接口是对接口的糟糕使用
如果你想导出常量，有几个合理的选择方案。

    如果常量与现有的类或接口紧密相关，则应将其添加到该类或 接口中。例如，所有数字基本类型的包装类，如 Integer 和 Double，都会导出 MIN_VALUE 和 MAX_VALUE
    常量。
    如果常量最好被看作枚举类型的成员，则应该使用枚举类型（详⻅第 34 条）导出它们。
    否则，你应该用一个不可实例化的工具类来导出常量（详⻅第 4 条）。

 知识点：

     请注意在数字文字中使用下划线字符（_）。从 Java 7 开始，合法的下划线对数字字面量的值没
     有影响，但是如果使用得当的话可以使它们更容易阅读。无论是固定的浮点数，如果他们包含五个或更多的连续
     数字，考虑将下划线添加到数字字面量中。对于底数为 10 的数字，无论是整型还是浮点型的，都应该用下划线将
     数字分成三个数字组，表示一千的正负幂。

```jshelllanguage
    class test {/** {@link javase.effectivejava.c21.testXiaHuaXian}*/}
```
### 23 【类层次结构优于标签类】 PREFER CLASS HIERARCHIES TO TAGGED CLASSES
tagged classes are verbose, error-prone, and inefficient.
带标记的类冗长、容易出错且效率低下。  
```jshelllanguage
    class test {/** {@link javase.effectivejava.c23.Figure}*/}
```
当遇到一个带有标签字段的现有类时，可以考虑将其重构为一个类层次结构

### 24 【倾向于使用静态成员类而不是非静态类】FAVOR STATIC MEMBER CLASSES OVER NONSTATIC
嵌套类（nested class）是在另一个类中定义的类。嵌套类应该只存在于其宿主类（enclosing class）中。
如果一 个嵌套类在其他一些情况下是有用的，那么它应该是一个顶级类。
有四种嵌套类：静态成员类，非静态成员类，匿 名类和局部类。
除了第一种以外，剩下的三种都被称为内部类（inner class）。
这个条目告诉你什么时候使用哪种类型的嵌套类以及为什么使用。
