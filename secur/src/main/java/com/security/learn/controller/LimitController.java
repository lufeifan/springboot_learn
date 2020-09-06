package com.security.learn.controller;

import com.security.learn.config.RateLimit;
import com.security.learn.dao.MyResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {
//    2个接口设定没秒限流5个和美妙限流10个

    @RateLimit(limitNum = 1.0)
    @RequestMapping("xian")
    public MyResult getResults() {
        return MyResult.OK("调用了方法", null);
    }
    @RequestMapping("xian1")
    @RateLimit(limitNum = 10.0)
    public MyResult getResultTwo() {
        return MyResult.OK("调用了方法getResultTwo", null);
    }
}
