package com.myblog.demo.controller;
import com.myblog.demo.entity.UserEntity;
import com.myblog.demo.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/testuser")
    public UserEntity testuser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123");
        userEntity.setUsername("123");
        return userEntity;
    }

    @RequestMapping("/del/{id}")
    public boolean delbannner1(@PathVariable String id){
        boolean b = bannerService.removeById(id);
        return b;
    }

    @RequestMapping("/string")
    public String testString(){
        return "stringtest";
    }
}
