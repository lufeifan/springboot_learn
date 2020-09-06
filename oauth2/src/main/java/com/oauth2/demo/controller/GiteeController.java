package com.oauth2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GiteeController {

    @GetMapping("/gitee_code")
    public String gitee(String code) {
        System.out.println(code);
        return "gitee";
    }
}
