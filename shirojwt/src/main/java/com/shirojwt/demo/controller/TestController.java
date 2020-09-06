package com.shirojwt.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shirojwt.demo.entity.User;
import com.shirojwt.demo.jwt.JwtUtil;
import com.shirojwt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/logintest/{username}/{password}")
    public String loginTest(@PathVariable String username,@PathVariable String password, HttpServletResponse response){
//       1. 到数据库查找相关的用户
        User user = userService.getOne(new QueryWrapper<User>().eq("name", username));
        if (user==null){
            return "不存在改用户";
        }
//        匹配密码是否正确
        if (!user.getPassword().equals(password)){
            return "密码错误";
        }
//       2.生成jwt
        String jwt = new JwtUtil().createToken(username);
//       3.返回携带有jwt 的请求信息给客户端
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
//       4.返回封装请求结果
        return "ok";
    }
}
