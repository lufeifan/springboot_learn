package com.shirojwt.demo.shiro;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.shirojwt.demo.entity.User;
import com.shirojwt.demo.jwt.JwtUtil;
import com.shirojwt.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//获取登录用户名
        return null;
    }

    //    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) authenticationToken;

        String token = (String) jwtToken.getPrincipal();
        /*从 token 中获取用户名*/
        String username = jwtUtil.getUsername(token);
        /*到数据库查询用户名是否存在*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",username);
        User user = userService.getOne(queryWrapper);
        /*用户不存在返回空，否则返回用户信息*/
        if (user==null){
            return null;
        }else {
            return new SimpleAuthenticationInfo(username, jwtToken.getCredentials(), getName());
        }
    }
}