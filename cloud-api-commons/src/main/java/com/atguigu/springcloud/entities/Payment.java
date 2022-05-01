package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther zzyy
 * @create 2020-02-18 17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable, Cloneable
{
    private Long id;
    private String serial;

    @Override
    protected Payment clone() throws CloneNotSupportedException {
        return (Payment) super.clone();
    }
}
