package com.security.learn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
//    认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("1020aA..")).roles("vip1")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("vip2");
    }
//授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//     /auth 需要vip1登录才能使用
        http.authorizeRequests().antMatchers("/auth").hasRole("vip2")
//                其他地址只要登录就可以使用
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout();

        http.authorizeRequests().antMatchers("/out").hasRole("vip1")
            .and()
            .formLogin();

        //登录成功的处理器
//        .successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                resp.setContentType("application/json;charset=utf-8");
//                PrintWriter out = resp.getWriter();
//                out.write("success");
//                out.flush();
//            }
//        })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write("fail");
//                        out.flush();
//                    }
//                })
//                .permitAll()//和表单登录相关的接口统统都直接通过
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        out.write("logout success");
//                        out.flush();
//                    }
//                })
//                .permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable();

    }
//不需要授权
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/err");
//        .ignoring().antMatchers("out");
    }
}