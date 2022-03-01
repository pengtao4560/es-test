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
