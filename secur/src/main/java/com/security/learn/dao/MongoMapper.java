package com.security.learn.dao;

import com.security.learn.entity.MongoEntity;

public interface MongoMapper {

    void saveDemo(MongoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(MongoEntity demoEntity);

    MongoEntity findDemoById(Long id);
}
