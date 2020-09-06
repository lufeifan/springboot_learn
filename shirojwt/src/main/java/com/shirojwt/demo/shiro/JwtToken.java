package com.shirojwt.demo.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    /**
     * principals:身份即主体的标识属性,可以是任何东西.如用户名,邮箱等.唯一即可.
     * 一个主体可以有多个principals，但只有一个Primary principals，一般是用户名/密码/手机号。
     * @return
     */

    @Override
    public Object getPrincipal() {

        System.out.println("++++++++++++++++++++getPrincipal+++++++++++++++++++++++");
        return token;
    }

    /**
     * credentials:证明/凭证，即只有主体知道的安全值。如密码
     * @return
     */
    @Override
    public Object getCredentials() {
        System.out.println("====================getCredentials==================");
        return token;
    }
}
