package com.cache.demo.service.impl;
 
import java.util.List;

import com.cache.demo.entity.User;
import com.cache.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

 
@Service
public class UserCache {
 
	@Autowired
	UserService userService;
 
	@Cacheable(value = "user", key = "'list'", unless = "#result == null") // unless="#result == null"表示返回结果为空时不缓存
	public List<User> getAll() {
		return userService.list();
	}
 
	@Cacheable(value = "user", key = "#id", unless = "#result == null") // unless="#result == null"表示返回结果为空时不缓存
	public User getOne(Integer id) {
		return userService.getById(id);
	}
 
	@CachePut(value = "user", key = "#entity.id") // 将返回结果更新到缓存中
	@CacheEvict(value = "user", key = "'list'") // list数据有变更，清空list缓存
	public User insert(User entity) {
		userService.save(entity);
		return entity;
	}
 
//	@CachePut(value = "user", key = "#entity.id") // 将返回结果更新到缓存中
//	@CacheEvict(value = "user", key = "'list'") // list数据有变更，清空list缓存
//	public User update(User entity) {
//		userService.update(entity);
//		return entity;
//	}
 
	@Caching(evict = { @CacheEvict(value = "user", key = "#id"), @CacheEvict(value = "user", key = "'list'") })
	// @CacheEvict(value = "user", key = "#id")//清除指定id的缓存
	// @CacheEvict(value="user", allEntries=true)//allEntries为true时，将忽略指定的key，清除全部user缓存
	public void delete(String id) {
		userService.removeById(id);
	}
 
}