package com.example.demo.lll.controller;


import com.example.demo.lll.service.impl.PlusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xiao Pengwei
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/lll/plus")
public class PlusController {

    @Autowired
    private PlusServiceImpl plusService;

    @RequestMapping("/addPerson")
    public Map<String, Object> plust(){
        return plusService.getMap(null);
    }

}
