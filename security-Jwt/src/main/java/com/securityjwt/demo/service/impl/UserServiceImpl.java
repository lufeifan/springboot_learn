package com.securityjwt.demo.service.impl;

import com.securityjwt.demo.entity.User;
import com.securityjwt.demo.mapper.UserMapper;
import com.securityjwt.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.securityjwt.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LuLuLua
 * @since 2020-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    public String login(String username,String password){
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new JwtUtil().createToken(username);
//    }
}
