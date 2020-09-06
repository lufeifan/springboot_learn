package com.example.demo.lll.service.impl;

import com.example.demo.lll.entity.User;
import com.example.demo.lll.mapper.UserMapper;
import com.example.demo.lll.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xiao Pengwei
 * @since 2020-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
