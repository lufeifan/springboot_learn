package com.example.demo.controller;

import com.example.demo.dao.PlusMapper;
import com.example.demo.entity.Plus;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    protected UserService userService;

    @RequestMapping("aaa")
    public List<User> getUser(){
        return userService.findAll();
    }

    @Autowired
    private PlusMapper plusMapper;

    @RequestMapping("delplus")
    public int delPlus(){
        return plusMapper.deleteBatchIds(Collections.singleton(1));
//        return plusMapper.selectList(null);
    }
    @RequestMapping("plusList")
    public List<Plus> getPlus(){
        return plusMapper.selectList(null);
    }

    @RequestMapping("selectList")
    public List<Plus> onePlus(){
        return plusMapper.selectBatchIds(Collections.singleton(3));
    }
    @RequestMapping("updata")
    public int updataPlus(){
        Plus plus = new Plus();
        plus.setName("nam");
        plus.setId(3);
        return plusMapper.updateById(plus);
    }
    @RequestMapping("insert")
    public int insertPlus(){
        Plus plus = new Plus();
        plus.setName("insert");
        return plusMapper.insert(plus);
    }
}
