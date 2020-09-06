package com.cache.demo.service.impl;

import com.cache.demo.entity.User;
import com.cache.demo.mapper.UserMapper;
import com.cache.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lululua
 * @since 2020-08-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
