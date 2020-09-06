package com.cache.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cache.demo.entity.User;
import org.springframework.stereotype.Component;

/**
 * JWT的token，区分大小写
 */

@Component
public class JwtConfig {

    public String createJwt(User user){
        String sign = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getName()));
        return sign;
    }
//获取用户名
    public String getid(String token){
        return JWT.decode(token).getAudience().get(0);
    }

//    验证
    public DecodedJWT yanzheng(User user, String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getName())).build();
        return jwtVerifier.verify(token);
    }

}