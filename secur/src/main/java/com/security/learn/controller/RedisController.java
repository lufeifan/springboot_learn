package com.security.learn.controller;

import com.security.learn.service.Impl.RedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import javax.annotation.Resource;

/**
 * @program: springbootdemo
 * @Date: 2019/2/22 15:03
 * @Author: zjjlive
 * @Description:
 */
@Slf4j
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisServiceImpl redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value){
        redisUtil.set("time",String.valueOf(new Date()));
        return redisUtil.expire("time",ExpireTime);
    }

    @RequestMapping("get")
    public Object redisget(String key){
//        从缓存里取出
        if (redisUtil.get("time")!=null) {
            return redisUtil.get("time");
        }
//        缓存中没有直接获取新数据
        else {
            return new Date();
        }
    }

}
