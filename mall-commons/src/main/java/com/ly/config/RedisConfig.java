package com.ly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author YiMeng
 * @DateTime: 2022/8/2 10:13
 * @Description: TODO
 */
@SuppressWarnings(value = {"all"})//屏蔽所有的吊毛警告
@Configuration
public class RedisConfig {



    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);//配置连接
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class)); //配置Value的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());//配置key的序列化方式
        return redisTemplate;
    }




}
