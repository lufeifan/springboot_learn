package com.security.learn.service.Impl;

import com.security.learn.dao.MongoMapper;
import com.security.learn.entity.MongoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * 描述: Demo DAO 实现
 *
 * @author yanpenglei
 * @create 2018-02-03 16:57
 **/
@Service
public class MongoServiceImpl implements MongoMapper {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(MongoEntity demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeDemo(Long id) {
        MongoEntity mongoEntity = new MongoEntity();
        mongoEntity.setId(id);
        mongoTemplate.remove(mongoEntity);
    }

    @Override
    public void updateDemo(MongoEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, MongoEntity.class);
    }

    @Override
    public MongoEntity findDemoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        MongoEntity demoEntity = mongoTemplate.findOne(query, MongoEntity.class);
        return demoEntity;
    }

}
