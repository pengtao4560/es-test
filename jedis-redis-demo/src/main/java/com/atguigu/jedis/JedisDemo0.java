package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * redis java 操作 redis
 *
 * @author pengtao
 * @createdate 2022/03/06 0006
 */
public class JedisDemo0 {

    public static void main(String[] args) {

        // 创建 Jedis 对象  import redis.clients.jedis.Jedis;

        String host = "192.168.159.132";
        int port = 6379;

        Jedis jedis = new Jedis(host, port);

        // 测试
        String value = jedis.ping();

        System.out.println(value); // PONG

        /**
         timeout报错：
         考虑防火墙放行端口
         firewall-cmd --permanent --add-port=6379/tcp

         firewall-cmd --reload

         Exception in thread "main" redis.clients.jedis.exceptions.JedisConnectionException:
         Failed to connect to any host resolved for DNS name.
         报错解决：需要把配置文件 redis.conf 设置好后  重启
         ps -ef|grep redis
         kill -9 pid
         redis-server /etc/redis.conf

         * */
    }

    /** 创建 Jedis 对象 */
    public static Jedis getRedisConnection() {
        String host = "192.168.159.132";
        int port = 6379;

        Jedis jedis = new Jedis(host, port);
        return jedis;
    }

    //操作zset
    @Test
    public void testZset() {
        //创建Jedis对象
        Jedis jedis = getRedisConnection();

        jedis.zadd("china", 100d, "shanghai");

        List<String> china = jedis.zrange("china", 0, -1);
        System.out.println(china);

        jedis.close();
    }

    //操作hash
    @Test
    public void testHash() {
        //创建Jedis对象
        Jedis jedis = getRedisConnection();

        jedis.hset("users","age","20");
        String hget = jedis.hget("users", "age");

        System.out.println(hget);
        System.out.println("操作类型：" + jedis.type("users"));

        jedis.close();
    }

    //操作set
    @Test
    public void testDemoSet() {
        //创建Jedis对象
        Jedis jedis = getRedisConnection();

        jedis.sadd("names","lucy");
        jedis.sadd("names","mary");

        Set<String> names = jedis.smembers("names");
        System.out.println(names);
        System.out.println("操作类型：" + jedis.type("names"));
        jedis.close();
    }

    //操作list
    @Test
    public void testRedisList() {
        //创建Jedis对象
        Jedis jedis = getRedisConnection();

        jedis.lpush("key1","lucy", "mary", "jack");

        List<String> values = jedis.lrange("key1", 0, -1);
        System.out.println("values: " + values);
        jedis.close();
    }

    //操作key string
    @Test
    public void demo1() {
        //创建Jedis对象
        Jedis jedis = getRedisConnection();

        //添加
        jedis.set("name", "lucy");

        //获取
        String name = jedis.get("name");
        System.out.println(name);

        //设置多个key-value
        jedis.mset("k1", "v1", "k2", "v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

        Set<String> keys = jedis.keys("*");
        for(String key : keys) {
            System.out.println("keys:" + key);
        }
        jedis.close();
    }
}
