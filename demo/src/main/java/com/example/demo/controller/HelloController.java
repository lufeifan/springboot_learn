package com.example.demo.controller;

import com.example.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
//Api注解，描述信息 可通过tag进行分类
@Api(value = "HelloController", description = "HelloController")
public class HelloController {

    @RequestMapping("/addPerson")
    @ApiOperation(notes = "添加人员", value = "addPerson")
    public User addPerson(
            @ApiParam(name = "name", value = "姓名") @RequestParam("name") String name,
            @ApiParam(name = "age", value = "年龄")  @RequestParam("age") Integer age) {
        User person = new User("swagger",1);
        return person;
    }
}