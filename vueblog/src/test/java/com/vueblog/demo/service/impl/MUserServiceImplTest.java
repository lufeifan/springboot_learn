package com.vueblog.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MUserServiceImplTest {

    @RestController
    @RequestMapping("/user")
    public class UserController {
        @Autowired
        private MBlogServiceImpl mBlogService;
        @GetMapping("/{id}")
        public Object test(@PathVariable("id") Long id) {
            return mBlogService.getById(id);
        }
    }
}