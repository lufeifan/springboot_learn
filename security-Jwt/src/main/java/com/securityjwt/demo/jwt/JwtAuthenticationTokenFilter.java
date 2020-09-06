//package com.securityjwt.demo.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.securityjwt.demo.entity.User;
//import com.securityjwt.demo.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = httpServletRequest.getHeader("Authorization");
//        /**
//         * tringUtils.isEmpty 为空返回真
//         */
//        if (authHeader!=null& StringUtils.isEmpty(authHeader)){
//            /**
//             * 获取token中的username
//             */
//            String username = jwtUtil.getUsername(authHeader);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                /**
//                 * 验证token
//                 */
//                if (jwtUtil.verify(authHeader,username)){
//                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                    SecurityContextHolder.getContext().setAuthentication(token);
//                }
//            }
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//
//    }
//}
