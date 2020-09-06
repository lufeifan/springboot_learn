package com.oauth2.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TsetHtml {

    @GetMapping("/github")
    public String test2() {
        return "github";
    }

    /**
     * 当我们点击 GitHub 图标，完成授权操作之后，会自动跳转到我们在准备工作填的回调地址中，
     * 并且携带一个 code 参数，拿着这个 code 参数我们就可以去获取 access_token 了，
     * 有了 access_token 我们就可以获取到用户信息了。
     * @param code
     * @return
     */
//    @GetMapping("/github_code")
//    public String authorization_code(String code) {
//        Map<String, String> map = new HashMap<>();
//        map.put("client_id", "7f6178a30d5726427163");
//        map.put("client_secret", "951fee61bed7cca5de68d682d59529745263002b");
//        map.put("state", "javaboy");
//        map.put("code", code);
//        map.put("redirect_uri", "http://localhost:8081/github_code");
//        System.out.println( Map.class);
//        Map<String,String> resp =new RestTemplate().postForObject("https://github.com/login/oauth/access_token", map, Map.class);
//
//        HttpHeaders httpheaders = new HttpHeaders();
//        httpheaders.add("Authorization", "token " + resp.get("access_token"));
//        HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
//        ResponseEntity<Map> exchange = new RestTemplate().exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
//        System.out.println("exchange.getBody() = " + exchange.getBody());
//
//        /**
//         * https://api.github.com/user?access_token=defae1dc85b77dd79936feb1df1a547e0e4fd511
//         * 登录成功个人信息地址
//         */
////        登录成功跳转到github页面
//        return "forward:/github";
//    }
}
