package com.learn.day1.service;


import com.learn.day1.dao.UserMapper;
import com.learn.day1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.findALl();
    }
}
