package com.example.demo.controller;

import com.example.demo.entity.Te;
import com.example.demo.service.TeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Tecontrol {
    @Autowired
    private TeService teService;

    @RequestMapping("te")
    public List<Te> getTe(){
        return teService.findAll();
    }
}
