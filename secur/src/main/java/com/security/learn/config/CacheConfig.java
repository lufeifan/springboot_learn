package com.security.learn.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializationContext.SerializationPair serializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(getRedisSerializer());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .serializeValuesWith(serializationPair);
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
//        return super.cacheManager();
    }


    @Override
    public CacheResolver cacheResolver() {
        return super.cacheResolver();
    }

    @Override
    public KeyGenerator keyGenerator() {
        return super.keyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return super.errorHandler();
    }
//    @Bean
//    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
//        RedisSerializationContext.SerializationPair serializationPair =
//                RedisSerializationContext.SerializationPair.fromSerializer(getRedisSerializer());
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(30))
//                .serializeValuesWith(serializationPair);
//        return RedisCacheManager
//                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
//                .cacheDefaults(redisCacheConfiguration).build();
//    }
//
    private RedisSerializer<Object> getRedisSerializer(){
        return new GenericFastJsonRedisSerializer();
    }


//    @Resource
//    private RedisConnectionFactory factory;
//
//    /**
//     * 自定义生成redis-key
//     *
//     * @return
//     */
//    @Override
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return (o, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(o.getClass().getName()).append(".");
//            sb.append(method.getName()).append(".");
//            for (Object obj : objects) {
//                sb.append(obj.toString());
//            }
//            System.out.println("keyGenerator=" + sb.toString());
//            return sb.toString();
//        };
//    }
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate() {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//
//        redisTemplate.setKeySerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
//
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
//        return redisTemplate;
//    }
//
//    @Bean
//    @Override
//    public CacheResolver cacheResolver() {
//        return new SimpleCacheResolver(cacheManager());
//    }
//
//    @Bean
//    @Override
//    public CacheErrorHandler errorHandler() {
//        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
//        return new SimpleCacheErrorHandler();
//    }
//
//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
//                        .defaultCacheConfig()
//                        .disableCachingNullValues()
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//        return RedisCacheManager.builder(factory).cacheDefaults(cacheConfiguration).build();
//
//    }
} 