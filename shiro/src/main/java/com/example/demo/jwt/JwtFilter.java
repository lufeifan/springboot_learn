package com.example.demo.jwt;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest request1 = (HttpServletRequest) response;
        String token = request1.getHeader("Authorization");
        if (StringUtils.isEmpty(token)){
            return null;
        }
        return new JwtToken(token);
    }

//    拦截
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest request1 = (HttpServletRequest) response;
        String token = request1.getHeader("Authorization");
        if (StringUtils.isEmpty(token)){
            return true;
        }else {
//            效验jwt

//            执行登录
        }
        return false;
    }
}
