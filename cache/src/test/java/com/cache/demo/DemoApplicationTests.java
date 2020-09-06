package com.cache.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cache.demo.config.JwtConfig;
import com.cache.demo.entity.User;
import com.cache.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;//操作k-v对象的

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("name","b");
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
    }

    @Cacheable(value = {"emp"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public User getEmp(String id){
        System.out.println("查询"+id+"号员工");
        return userService.getById(id);
    }

}
