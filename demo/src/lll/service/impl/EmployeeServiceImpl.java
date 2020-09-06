package com.example.demo.lll.service.impl;

import com.example.demo.lll.entity.Employee;
import com.example.demo.lll.mapper.EmployeeMapper;
import com.example.demo.lll.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
