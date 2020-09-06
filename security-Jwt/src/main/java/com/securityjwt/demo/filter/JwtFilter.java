package com.securityjwt.demo.filter;

import com.securityjwt.demo.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("authorization");

//        System.out.println(jwtToken);
//        System.out.println(jwtToken.replace("Bearer",""));
       try {
           String username = new JwtUtil().getUsername(jwtToken.replace("Bearer",""));
           /**
            * 获取roles
            */
//        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) new JwtUtil().getAuthorities(jwtToken.replace("Bearer","")));
           List<SimpleGrantedAuthority> authorities = new JwtUtil().getUserRolesByToken(jwtToken.replace("Bearer",""));
           System.out.println(authorities);
           UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authorities, username, authorities);
           SecurityContextHolder.getContext().setAuthentication(token);
       } catch (Exception e) {
//           e.printStackTrace();

       }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
