package com.shirojwt.demo.service.impl;

import com.shirojwt.demo.entity.Role;
import com.shirojwt.demo.mapper.RoleMapper;
import com.shirojwt.demo.service.RoleService;
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
