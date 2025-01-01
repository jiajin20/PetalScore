package com.cf.huaban.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key";  // 设置一个强密码

    // 生成 JWT Token
    public static String createToken(String account) {
        // 使用 HMAC256 算法进行签名
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withClaim("account", account)  // 设置自定义字段
                .withIssuer("auth0")            // 设置发布者
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))  // 设置过期时间 1 小时
                .sign(algorithm);               // 使用算法签名
    }
    // 验证 JWT Token
    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);  // 验证并解析 Token
        } catch (Exception e) {
            throw new RuntimeException("Token is invalid or expired");

        }
    }

}
