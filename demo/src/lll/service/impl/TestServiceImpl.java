package com.example.demo.lll.service.impl;

import com.example.demo.lll.entity.Test;
import com.example.demo.lll.mapper.TestMapper;
import com.example.demo.lll.service.ITestService;
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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
