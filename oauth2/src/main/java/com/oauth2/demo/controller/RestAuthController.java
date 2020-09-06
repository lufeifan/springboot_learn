package com.oauth2.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthOschinaRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Controller
public class RestAuthController {
    /**
     * gitee
     * @return
     */
    @GetMapping("/gitee")
    public String gitee() {
        return "gitee";
    }

//    授权页面
//    @RequestMapping("/render/{source}")
//    public void renderAuth(HttpServletResponse response) throws IOException {
//        AuthRequest authRequest = getAuthRequest();
//        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
//    }
//
//    @GetMapping("/callback/{source}")
//    public void login(String code,HttpServletResponse response) throws IOException {
//        Map<String, String> map = new HashMap<>();
//        map.put("msg","登录成功");
//        map.put("data",code);
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        out.write(new ObjectMapper().writeValueAsString(map));
//        out.flush();
//        out.close();
//    }

//    private AuthRequest getAuthRequest() {
//        return new AuthGiteeRequest(AuthConfig.builder()
//                .clientId("3cee9e11851333e5cc728faddd1d4a01de1a583cec4bf4cce111cf4bab7875e5")
//                .clientSecret("d78a155704aa2a2fb9fa53fbd015f1ba848204ad71b930159d0d2a1c5a710b6d")
//                .redirectUri("http://localhost:8081/callback/gitee")
//                .build());
//    }

    /**
     * github
     * @param response
     * @throws IOException
     */

    @RequestMapping("/github_render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/github_code")
    public Object github_code(String code) {
        /**
         * 授权码
         */
        System.out.println(code);
        return "github";
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("7f6178a30d5726427163")
                .clientSecret("951fee61bed7cca5de68d682d59529745263002b")
                .redirectUri("http://localhost:8081/github_code")
                .build());
    }

    /**
     * oschina
     */

//    @RequestMapping("/oschina_render")
//    public void renderAuth(HttpServletResponse response) throws IOException {
//        AuthRequest authRequest = getAuthRequest();
//        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
//    }
//
//    @RequestMapping("/oschina_code")
//    public Object login(String code) {
//        System.out.println(code);
//        return "github";
//    }
//
//    private AuthRequest getAuthRequest() {
//        return new AuthOschinaRequest(AuthConfig.builder()
//                .clientId("nddPw6h1Moq3B6yiM2lK")
//                .clientSecret("8TPFxCXyOip62l2yxTHsBVLCYgYQ6zj0")
//                .redirectUri("http://localhost:8081/oschina_code")
//                .build());
//    }
    /**
     *  @RequestMapping("/render")
     *     public void renderAuth(HttpServletResponse response) throws IOException {
     *         AuthRequest authRequest = getAuthRequest();
     *         response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
     *     }
     *
     *     @RequestMapping("/callback")
     *     public Object login(AuthCallback callback) {
     *         AuthRequest authRequest = getAuthRequest();
     *         return authRequest.login(callback);
     *     }
     *
     *     private AuthRequest getAuthRequest() {
     *         return new AuthGiteeRequest(AuthConfig.builder()
     *                 .clientId("Client ID")
     *                 .clientSecret("Client Secret")
     *                 .redirectUri("应用回调地址")
     *                 .build());
     *     }
     */
}
