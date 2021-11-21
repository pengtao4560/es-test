package com.atguigu.springcloud.dao;



import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author pt
 * @createdate 2021/11/19 0019
 * @desc
 */
@Mapper //import org.apache.ibatis.annotations.Mapper;
public interface PaymentDao {
    /* 写操作*/
    public int create(Payment payment);
    /** 读操作*/
    public Payment getPaymentById(@Param("id") Long id);
}


