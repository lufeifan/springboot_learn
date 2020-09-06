package com.learn.day1.controller;

import com.learn.day1.entity.User;
import com.learn.day1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PmsBrandController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public List<User> getUser(){
        return userService.findAll();
    }

//    @Autowired
//    private PmsBrandService demoService;

//    @RequestMapping("cc")
//    public CommonResult<List<PmsBrand>> getBrandList() {
//        return CommonResult.success(demoService.listAllBrand());
//    }

//    @RequestMapping(value = "/dd", method = RequestMethod.GET)
//    @ResponseBody
//    public String test() {
//        return "test";
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
//        return CommonResult.success(demoService.getBrand(id));
//    }
}
