package com.shirojwt.demo.service.impl;

import com.shirojwt.demo.entity.Permission;
import com.shirojwt.demo.mapper.PermissionMapper;
import com.shirojwt.demo.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author LuLuLua
 * @since 2020-08-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
