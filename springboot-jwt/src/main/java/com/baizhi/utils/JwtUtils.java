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
