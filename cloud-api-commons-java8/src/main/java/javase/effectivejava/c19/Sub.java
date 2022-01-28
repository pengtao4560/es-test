package javase.effectivejava.c19;

import java.time.Instant;

/**
 * effective java 19【要么设计继承并提供文档说明，要么禁用继承】
 * 【类必须遵守允许继承的限制】构造方法绝不能直接或间接调用可重写的方法 (final方法和静态方法不可被重写，所以可以调用。)
 *  【在没有想要安全地子类化的设计和文档说明的类中禁止子类化】禁止子类化的方法
 *  1.声明类final 2.所有的构造方法都是私有或者包级私有
 * 原因演示类
 * @author: peng tao
 * @create: 2022-01-28 11:48
 */
public final class Sub extends Super {
    // Blank final, set by constructor
    private final Instant instant;
    Sub() {
        instant = Instant.now();
    }
    // Overriding method invoked by superclass constructor
    @Override public void overrideMe() {
        System.out.println(instant);
    }
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
        //
    }
}


class Super { // Broken - constructor invokes an overridable method public
    Super() {
        overrideMe();
    }
    public void overrideMe() {

    }
}
