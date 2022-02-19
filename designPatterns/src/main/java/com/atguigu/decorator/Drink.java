package com.atguigu.decorator;

import lombok.Data;

/**
 * 装饰者模式案例实体类 - 饮品类
 *
 * @author pengtao
 * @createdate 2022/02/19 0019
 */
@Data
public abstract class Drink {

    /** 描述 */
    public String description;

    /** 价格 */
    private float price = 0.0F;

    /** 计算费用 */
    public abstract float cost();
}
