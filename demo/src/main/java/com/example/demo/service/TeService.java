package com.example.demo.service;

import com.example.demo.dao.TeMapper;
import com.example.demo.entity.Te;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeService {
    @Autowired
    private TeMapper teMapper;

    public List<Te> findAll(){
        return teMapper.findAll();
    }
}
