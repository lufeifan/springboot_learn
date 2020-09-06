package com.securityjwt.demo.controller;

import com.securityjwt.demo.service.UserService;
import com.securityjwt.demo.service.impl.UserServiceImpl;
import com.securityjwt.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@ResponseBody
@Controller
public class TestController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Autowired
//    private UserServiceImpl userService;

//    @RequestMapping("/tt/{username}/{password}")
//    public String hello(@PathVariable String username, @PathVariable String password, HttpServletResponse response){
//        String token =new JwtUtil().createToken(username);
////        携带token返回
//        response.setHeader("Authorization", token);
//        response.setHeader("Access-control-Expose-Headers", "Authorization");
//        return token;
//    }
//    @RequestMapping("/tt/{username}/{password}")
//    public String hello(@PathVariable String username,@PathVariable String password){
//        return username+password;
//    }
    @RequestMapping("/hello")
    public String hello1(){
        return "hello world 1";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "hello admin 1";
    }

    @RequestMapping("/name")
    public String name(){
        return "hello name 1";
    }

    @RequestMapping("/jwt")
    public String jwt(){
        return "hello jwt";
    }

    @RequestMapping("/test")
    public String test(){
        return "hello test";
    }

    @RequestMapping("/wwwa")
    public String index(){
        return "index";
    }
}
