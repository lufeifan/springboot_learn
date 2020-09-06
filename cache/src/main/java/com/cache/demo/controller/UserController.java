package com.cache.demo.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.cache.demo.config.JwtConfig;
import com.cache.demo.Util.JwtUtil;
import com.cache.demo.entity.User;
import com.cache.demo.service.UserService;
import com.cache.demo.service.impl.UserCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lululua
 * @since 2020-08-17
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCache userCache;

//    @RequiresPermissions("user:add")
    @Cacheable(value = "user", key = "'list'", unless = "#result == null")
    @RequestMapping("/get")
    public List<User> getuser(){
        return userService.list();
    }


    @RequestMapping("/get1")
    public List<User> getuser1(){
        return userCache.getAll();
    }

    @RequestMapping("/login")
    public String login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getName(),
                user.getId()
        );
        subject.login(token);
        return "login success";
    }

    @RequestMapping("/logout1")
    public String logout(){
        return "logout success";
    }



    @RequestMapping("/jwt")
    public String jwt(){
        User user = new User();
        user.setName("jwt");
        user.setId("666");
        JwtUtil jwtUtil = new JwtUtil();
        String sign = jwtUtil.sign(user);
        return sign;

//        JwtConfig jwtConfig = new JwtConfig();
//        String jwt = jwtConfig.createJwt(user);
//        return jwt;
    }

    @RequestMapping("/jwtid")
    public String jwtid(){
        JwtConfig jwtConfig = new JwtConfig();
        String jwt = jwtConfig.getid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NjYifQ.plJfg20z_iJ62kOAorqvGhuaISYKzYi5PgEtvOwrMMQ");
        return jwt;
    }
    @RequestMapping("/yanzheng")
    public DecodedJWT yanzheng(){
        JwtUtil jwtUtil = new JwtUtil();
        DecodedJWT jwt = jwtUtil.verity(jwt());
        return jwt;
    }
    @RequestMapping("/jwtver")
    public DecodedJWT jwtver(){
        User user = new User();
        user.setName("jwt");
        user.setId("666");

        JwtConfig jwtConfig = new JwtConfig();
        DecodedJWT jwt = jwtConfig.yanzheng(user,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NjYifQ.plJfg20z_iJ62kOAorqvGhuaISYKzYi5PgEtvOwrMMQ");
        return jwt;
    }
}

