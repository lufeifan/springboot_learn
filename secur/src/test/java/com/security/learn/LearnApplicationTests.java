package com.security.learn;

import com.alibaba.fastjson.JSONObject;
import com.security.learn.entity.MongoEntity;
import com.security.learn.entity.User;
import com.security.learn.service.Impl.MongoServiceImpl;

import com.security.learn.service.Impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@SpringBootTest
class LearnApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    @Cacheable(value = "user")
    public void findUserTest() {
//        for (int i = 0; i < 3; i++) {
//            System.out.println("第" + i + "次");
            findUser();
//            List<User> user = userService.list();
//            System.out.println(user);
//        }
    }

//    @Cacheable(value = {"valueName", "valueName2"}, key = "'keyName1'")
    public boolean findUser() {
        System.out.println("执行方法...");
        return userService.save(new User("id1", "张三",22, 18));
    }
    /**
     * 注入发送邮件的接口
     */
//    @Autowired
//    private IMailServiceImpl mailService;

    /**
     * 测试发送文本邮件
     */
//    @Test
//    public void sendmail() {
//        mailService.sendSimpleMail("1714004230@qq.com","主题：你好普通邮件","内容：第一封邮件");
//    }
//
//    @Test
//    public void sendmailHtml(){
//        mailService.sendHtmlMail("1714004230@qq.com","主题：你好html邮件","<h1>内容：第一封html邮件</h1>");
//    }

    @Autowired
    private MongoServiceImpl mongoMapper;

    @Test
    void contextLoads() {
        MongoEntity demoEntity = new MongoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        mongoMapper.saveDemo(demoEntity);

        demoEntity = new MongoEntity();
        demoEntity.setId(2L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        mongoMapper.saveDemo(demoEntity);

    }
    @Test
    public void removeDemoTest() {
        mongoMapper.removeDemo(2L);
    }

    @Test
    public void updateDemoTest() {

        MongoEntity demoEntity = new MongoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB 更新数据");
        demoEntity.setDescription("关注公众号，搜云库，专注于开发技术的研究与知识分享");
        demoEntity.setBy("souyunku");
        demoEntity.setUrl("http://www.souyunku.com");

        mongoMapper.updateDemo(demoEntity);
    }

    @Test
    public void findDemoByIdTest() {

        MongoEntity demoEntity = mongoMapper.findDemoById(1L);
//      将实体对象转换成Json字符串
        System.out.println(JSONObject.toJSONString(demoEntity));
    }

}
