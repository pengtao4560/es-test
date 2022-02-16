package com.atguigu.prototype.deepclone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式-深拷贝
 *
 * @author pengtao
 * @createdate 2022/02/16 0016
 */
public class DeepProtoType implements Serializable, Cloneable {

    public String name;
    public DeepCloneableTarget deepCloneableTarget;

    public DeepProtoType() {
    }

    /** 深拷贝-方式1 使用clone方法*/
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        // 完成对基本数据类型(属性)和String 的克隆
        deep = super.clone();
        /** 对引用类型的属性，进行单独处理 */
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();

        return deep;
    }

    /** 深拷贝-方式2 通过对象的序列化实现（推荐）*/

    public Object deepClone() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            /* 序列化 */
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // 当前这个对象以对象流的方式输出
            objectOutputStream.writeObject(this);

            /* 反序列化 */
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            DeepProtoType copyDeepProtoType = (DeepProtoType) objectInputStream.readObject();

            return  copyDeepProtoType;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {
            // 关闭流
            try {
                byteArrayOutputStream.close();
                objectOutputStream.close();
                objectInputStream.close();
                byteArrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                // TODO  使用 try-with-resource替换 try finally
            }
        }


    }

}
