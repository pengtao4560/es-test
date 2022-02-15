package com.atguigu.bean.pizza;

/**
 * 简单工厂模式
 *
 * @author pengtao
 * @createdate 2022/02/13 0013
 */
/** 将Pizza做成抽象的 */
public abstract class Pizza {
    /** Pizza名字 */
    protected String name;

    /** 准备原材料, 不同的披萨不一样，因此，我们做成抽象方法 */
    public abstract void prepare();

    public void bake() {
        System.out.println(name + " baking;");
    }

    public void cut() {
        System.out.println(name + " cutting;");
    }

    /** 打包 */
    public void box() {
        System.out.println(name + " boxing;");
    }

    public void setName(String name) {
        this.name = name;
    }

}
