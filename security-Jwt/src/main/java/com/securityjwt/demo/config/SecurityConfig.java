package com.securityjwt.demo.config;

import com.securityjwt.demo.filter.JwtFilter;
import com.securityjwt.demo.filter.JwtLoginFilter;
//import com.securityjwt.demo.jwt.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    //授权
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("admin")
                .and()
                .withUser("lllll").password(new BCryptPasswordEncoder().encode("admin")).roles("root");
//                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("vip2");
    }

//    认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").hasRole("admin")
                .antMatchers("/admin").hasRole("root")
                .antMatchers("/jwt").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .addFilterBefore(new JwtLoginFilter("/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class)
//                .csrf().disable();

    }
//不需要授权
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/test")
        .antMatchers("/index.html")
        .antMatchers("/wwwa");
//        .ignoring().antMatchers("out");
    }
}