哔哩哔哩-java技术栈学习-JWT java web token
[【编程不良人】JWT认证原理、流程整合springboot实战应用,前后端分离认证的解决方案!](https://www.bilibili.com/video/BV1i54y1m7cP?p=1)

[jwt官网](https://jwt.io/)

[jwt官网-jwt介绍](https://jwt.io/introduction)

引入java包
```xml
     <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.4.1</version>
        </dependency>
```

测试类
```java

package com.baizhi.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt测试
 *  header + payloader + signature
 * @author pengtao
 * @createdate 2022/03/01 0001
 */
public class SpringBootJwtTest {

    public static final String TOKEN_SECRET = "token!fjasoiu";
    public static final String MOCK_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjgwLCJ1c2VybmFtZSI6IuW8oOS4iSJ9.1LM0c0yozodry6zNBBkbskvt_8XhUI7DHYZWGlcGRs0";

    @Test
    public void testGetToken(){

        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 30);

        String token = JWT.create()
                //请求头 header
                .withHeader(map)
                // payload
                .withClaim("userId", 1) //
                .withClaim("username", "张三")
                // 指定令牌的过期时间
                //.withExpiresAt(instance.getTime())
                //signature
                .sign(Algorithm.HMAC256(TOKEN_SECRET));

        System.out.println(token);
    }

    @Test
    public void testCheckToken(){

        /** 创建验证对象 */
        Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));

        JWTVerifier jwtVerifier = verification.build();

        DecodedJWT verify = jwtVerifier.verify(MOCK_TOKEN);

        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());

        Map<String, Claim> claims = verify.getClaims();
        System.out.println(claims);

        for (Map.Entry<String, Claim> stringClaimEntry : claims.entrySet()) {
            System.out.println("key: " + stringClaimEntry.getKey() + "value: " + stringClaimEntry.getValue());
        }
        System.out.println(verify.getExpiresAt());

        // 如果算法不一致，异常 签名不一致 如果秘钥不一致， 异常 秘钥不一致
        /**
         常见异常信息
         signatureverificationException :签名不一致异常
         TokenExpiredException : 令牌过期异常
         AlgorithmMismatchException : 算法不匹配异常
         InvalidclaimException : 失效的payload异常

         * */
    }
}


```
工具类

```java
package com.baizhi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * jwt 工具类
 *
 * @author pengtao
 * @createdate 2022/03/01 0001
 */
public class JwtUtils {

    private static final String SECRET = "!jhKAFJQ2W95RLAFJAPJ";

    /**
     * 生成token header.payload.signature
     */

    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        // 指定令牌的过期时间
        String token = builder.withExpiresAt(instance.getTime())
                //signature
                .sign(Algorithm.HMAC256(SECRET));
        return  token;
    }

    /**
     * 验证token
     * 可以和 验证token合法性方法合并, 只需要方法
     * @see #getTokenInfo
     */
    @Deprecated
    public static void verify(String token) {

        /** 创建验证对象 */
        JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        // 如果验证有任何一个异常就抛出异常了
    }

    /**
     * 获取token 信息的方法
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }
}

```
