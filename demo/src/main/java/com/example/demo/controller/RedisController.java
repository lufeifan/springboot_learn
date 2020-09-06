package com.example.demo.controller;

import com.example.demo.service.RedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springbootdemo
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisServiceImpl redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value){
        return redisUtil.set("time","120");
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get("k");
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire("time",ExpireTime);
    }
}
