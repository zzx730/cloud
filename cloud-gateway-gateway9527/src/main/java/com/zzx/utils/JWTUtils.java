package com.zzx.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtils {
    // 签发人
    private static final String ISSUSER = "zzx";
    // 过期时间 1分钟
    private static final long TOKEN_EXPIRE_TIME = 60*1000;
    // 秘钥
    public static final String SECRET_KEY = "zzx-13256";
    /**
     * 生成令牌
     * @return
     */
    public static String token(){
        Date now = new Date();
        Algorithm hmac256 = Algorithm.HMAC256(SECRET_KEY);
        // 1.创建JWT
        String token = JWT.create().
                // 签发人
                withIssuer(ISSUSER)
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime()+TOKEN_EXPIRE_TIME))
                // 加密算法
                .sign(hmac256);
        return token;
    }

    /**
     * 验证令牌
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm hmac256 = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(hmac256)
                    // 签发人
                    .withIssuer(ISSUSER)
                    .build();
            // 如果校验有问题则抛出异常
            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return false;
    }

}
