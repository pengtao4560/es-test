package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * 完成一个手机验证码功能
 * 要求：
 * 1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
 * 2、输入验证码，点击验证，返回成功或失败
 * 3、每个手机号每天只能输入3次
 */
/*
1、生成随机6位数字验证码Random
2、验证码在2分钟内有效
**把验证码放到redis里面，设置过期时间120秒
3、判断验证码是否一致
**从redis获取验证码和输入的验证码进行比较
4、每个手机每天只能发送3次验证码
** incr每次发送之后+1
**大于2时候,提交不能发送

* */
public class PhoneCode {

    //模拟验证码校验
    @Test
    public void testGetRedisCode(){
        getRedisCode("13085604560","699014");
    }

    //模拟验证码校验
    @Test
    public void testVerifyCode() {
        String code = verifyCode("13085604560");
        System.out.println(code);

    }

    /**
     * 3 验证码校验
     * */
    public static void getRedisCode(String phone, String code) {
        //从redis获取验证码
        Jedis jedis = JedisDemo0.getRedisConnection();
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        //判断
        if (redisCode.equals(code)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        jedis.close();
    }

    /**
     * 2 每个手机每天只能发送三次，验证码放到redis中，设置过期时间120
     * */
    public static String verifyCode(String phone) {
        //连接redis
        Jedis jedis = JedisDemo0.getRedisConnection();

        //拼接key
        //手机发送次数key
        String countKey = "VerifyCode" + phone + ":count";
        //验证码key
        String codeKey = "VerifyCode" + phone + ":code";

        //每个手机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            //没有发送次数，第一次发送
            //设置发送次数是1
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            //发送次数+1
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            //发送三次，不能再发送
            System.out.println("今天发送次数已经超过三次");
            jedis.close();
            return "今天发送次数已经超过三次";
        }

        //发送验证码放到redis里面并设置过期时间
        String vcode = getCode();
        jedis.setex(codeKey, 120, vcode);
        jedis.close();
        return vcode;
    }

    /**
     * 生成6位数字验证码
     */
    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }
}
