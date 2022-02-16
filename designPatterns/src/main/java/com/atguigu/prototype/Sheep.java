package com.atguigu.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 羊 (原型模式demo 实体类)
 *
 * @author pengtao
 * @createdate 2022/02/15 0015
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Sheep implements Cloneable {

    private String name;
    private int age;
    private String color;

    /** 克隆该实例，使用默认的clone方法来完成*/
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            log.info("e: {}", e.getMessage());
        }
        return sheep;
    }
}
