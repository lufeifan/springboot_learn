package com.securityjwt.demo.service.impl;

import com.securityjwt.demo.entity.Role;
import com.securityjwt.demo.mapper.RoleMapper;
import com.securityjwt.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LuLuLua
 * @since 2020-08-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
