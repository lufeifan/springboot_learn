package com.example.demo.controller;

import com.example.demo.entity.Plus;
import com.example.demo.service.PlusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlusController {

    @Autowired
    private PlusServiceImpl plusService;

    @RequestMapping("/plusnn")
    public List<Plus> getpluss(){
        return plusService.list(null);
    }
}
