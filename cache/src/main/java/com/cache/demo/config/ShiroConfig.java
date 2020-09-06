package com.cache.demo.config;

import com.cache.demo.shiro.MySRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//   自定义验证方式
    @Bean
    public AuthorizingRealm mySRealm(){
        MySRealm mySRealm = new MySRealm();
        return mySRealm;
    }
// 权限管理
    @Bean
    public DefaultSecurityManager mysecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(mySRealm());
        return securityManager;
    }
    /**
     * Shiro内置过滤器，可以实现权限相关的拦截器
     *    常用的过滤器：
     *       anon: 无需认证（登录）可以访问
     *       authc: 必须认证才可以访问
     *       user: 如果使用rememberMe的功能可以直接访问
     *       perms： 该资源必须得到资源权限才可以访问
     *       roles: 该资源必须得到角色权限才可以访问
     */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(mysecurityManager());

        Map<String, String> map = new LinkedHashMap<>();
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/get", "authc");
//        map.put("/get", "perms[user:add]");
        map.put("/get", "roles[admin]");
        //登录
        bean.setLoginUrl("/login");
//        //首页
//        bean.setSuccessUrl("/get1");
//        //错误页面，认证不通过跳转
//        bean.setUnauthorizedUrl("/error");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(mysecurityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
