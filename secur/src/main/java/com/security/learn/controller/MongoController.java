package com.security.learn.controller;

import com.security.learn.entity.MongoEntity;
import com.security.learn.service.Impl.MongoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {

    @Autowired
    private MongoServiceImpl mongoService;
    @RequestMapping("mongo")
    public MongoEntity findDemoByIdTest() {

        MongoEntity demoEntity = mongoService.findDemoById(1L);
//      将实体对象转换成Json字符串
//        return JSONObject.toJSONString(demoEntity);
        return demoEntity;
    }
    @RequestMapping("mongosave")
    public MongoEntity mongosave() {
        MongoEntity demoEntity = new MongoEntity();
        demoEntity.setId(5L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");
        mongoService.saveDemo(demoEntity);
        return demoEntity;
    }
}
