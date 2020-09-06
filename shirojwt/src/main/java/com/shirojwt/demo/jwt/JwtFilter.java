package com.shirojwt.demo.jwt;

import com.shirojwt.demo.shiro.JwtToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtFilter extends AuthenticatingFilter {
    /**
     * 第二步
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        获取客户端的jwt
        String jwt = request.getHeader("Authorization");
        System.out.println("createToken jwt:"+jwt);
        if(StringUtils.isEmpty(jwt)) {
            return null;
        }
//        返回jwt
        return new JwtToken(jwt);
    }
//进行信息效验，效验成功执行登录

    /**
     * 第一步
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        System.out.println("onAccessDenied jwt:"+jwt);
        /**
         * jwt 为空抛出异常
         */
        if(StringUtils.isEmpty(jwt)) {
            throw new ExpiredCredentialsException("token为空");
//            return false;
        } else {
        /**
         * 校验jwt是否正确，是否过期
         */
            boolean claim = new JwtUtil().verify(jwt, new JwtUtil().getUsername(jwt));
            if (claim){
                System.out.println("true");
                return executeLogin(servletRequest, servletResponse);
            }else {
                throw new ExpiredCredentialsException("token不合法");
            }
            // 执行登录

        }
    }
}
