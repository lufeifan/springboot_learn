package com.cache.demo.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cache.demo.entity.User;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    /**
     * 过期时间为1天
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * Token私钥
     */
    private static final String TOKEN_SECRET = "onesmile123123123";

    /**
     * 生成签名，15分钟后过期
     * @param user
     */
    public String sign(User user){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String,Object> header = new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        //附带username和userId生成签名
        return JWT.create().withHeader(header).withClaim("loginName",user.getName())
                .withClaim("userId",user.getId()).withExpiresAt(date).sign(algorithm);
    }

    public DecodedJWT verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}