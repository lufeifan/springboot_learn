package com.vueblog.demo.controller;


import com.vueblog.demo.service.MUserService;
import com.vueblog.demo.service.impl.MBlogServiceImpl;
import com.vueblog.demo.service.impl.MUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lululua
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/user")
public class MUserController {

        @Autowired
        private MUserService mUserService;

        @GetMapping("/{id}")
        public Object test(@PathVariable("id") int id) {
            return mUserService.getById(id);
        }
}

