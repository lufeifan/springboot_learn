package com.myblog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FileController {



    @RequestMapping("/index")
    public String gethtml(){
        return "index.html";
    }
}
