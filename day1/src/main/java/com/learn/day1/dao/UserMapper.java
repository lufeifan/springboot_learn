package com.learn.day1.dao;

import com.learn.day1.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findALl();
}
