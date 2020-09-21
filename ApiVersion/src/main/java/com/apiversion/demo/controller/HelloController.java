package com.apiversion.demo.controller;

import com.apiversion.demo.hander.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{v2}")
public class HelloController {

    /**
     *
     * @return
     */
//    @RequestMapping("/demo1/test/{version}")
//    @ApiVersion(1.0)
//    public String demo1(){
//        return "demo1";
//    }
//
//    @RequestMapping("/demo1/test/{version}")
//    @ApiVersion(2.0)
//    public String demo2(){
//        return "demo2";
//    }
//
//    @RequestMapping("/demo1/test/{version}")
//    @ApiVersion(3.0)
//    public String demo3(){
//        return "demo3";
//    }

    @RequestMapping("/demo4")
//    @ApiVersion(1)
    public String demo4(){
        return "demo4";
    }

    @RequestMapping("/demo4")
    @ApiVersion(2)
    public String demo5(){
        return "demo5";
    }
}
