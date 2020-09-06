package com.shirojwt.demo.service.impl;

import com.shirojwt.demo.entity.User;
import com.shirojwt.demo.mapper.UserMapper;
import com.shirojwt.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LuLuLua
 * @since 2020-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
