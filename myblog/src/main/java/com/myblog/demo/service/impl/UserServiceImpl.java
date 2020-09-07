package com.myblog.demo.service.impl;

import com.myblog.demo.entity.UserEntity;
import com.myblog.demo.mapper.UserMapper;
import com.myblog.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lululua
 * @since 2020-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
