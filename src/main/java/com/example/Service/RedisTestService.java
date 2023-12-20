package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTestService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void saveData(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    public String getData(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
