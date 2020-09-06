package com.security.learn.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.learn.dao.UserMapper;
import com.security.learn.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2020-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}