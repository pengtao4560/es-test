package com.atguigu.prototype.deepclone;

import lombok.extern.slf4j.Slf4j;

/**
 * 深拷贝调用
 *
 * @author pengtao
 * @createdate 2022/02/16 0016
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {

        DeepProtoType deepProtoType1 = new DeepProtoType();
        deepProtoType1.name = "林冲";
        deepProtoType1.deepCloneableTarget = new DeepCloneableTarget("李逵", "李逵的类");

        // 方式1 完成深拷贝

        DeepProtoType deepProtoType2 = (DeepProtoType) deepProtoType1.clone();

        log.info("deepProtoType1.name = {}, deepProtoType1.deepCloneableTarget = {},", deepProtoType1.name, deepProtoType1.deepCloneableTarget.hashCode());
        log.info("deepProtoType2.name = {}, deepProtoType2.deepCloneableTarget = {},", deepProtoType2.name, deepProtoType2.deepCloneableTarget.hashCode());

        /* 深拷贝 - 方式2 通过对象的序列化实现（推荐）*/

        DeepProtoType deepProtoType3 = (DeepProtoType) deepProtoType1.deepClone();

        log.info("deepProtoType1.name = {}, deepProtoType1.deepCloneableTarget = {},", deepProtoType1.name, deepProtoType1.deepCloneableTarget.hashCode());
        log.info("deepProtoType3.name = {}, deepProtoType3.deepCloneableTarget = {},", deepProtoType3.name, deepProtoType3.deepCloneableTarget.hashCode());

    }
}
