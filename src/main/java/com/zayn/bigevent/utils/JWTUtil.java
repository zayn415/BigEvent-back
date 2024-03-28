package com.zayn.bigevent.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Map;

/**
 * @author zayn
 * * @date 2024/3/27/21:09
 */
public class JWTUtil {
    private static final String key = "com.zayn.bigevent";
    public static String createToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims) // 信息
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 3600 * 1000 * 12)) // 超时时间 12 hour
                .sign(Algorithm.HMAC256(key)); // 设置算法和密钥
    }

    public static boolean verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token) != null;
    }
    
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
