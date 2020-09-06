package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.PlusMapper;
import com.example.demo.entity.Plus;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2020-08-09
 */
@Service
public class PlusServiceImpl extends ServiceImpl<PlusMapper, Plus> implements IPlusService {

}
