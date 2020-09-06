package com.cache.demo.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cache.demo.entity.User;
import com.cache.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MySRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行————--------------------------doGetAuthorizationInfo2");
        return null;
    }

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行————-------------------------doGetAuthenticationInfo1");

        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        User user = userService.getOne(queryWrapper);
        if (user==null){
            return null;
        }else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,user.getId().toString(),getName());
            return simpleAuthenticationInfo;
        }

    }
}
