package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisConfig {
    @Bean(name = "redis1ConnectionFactory")
    public LettuceConnectionFactory redis1ConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("127.0.0.1", 6380);
        return new LettuceConnectionFactory(config);
    }

    @Bean(name = "redis1Template")
    public RedisTemplate redisTemplate(@Qualifier("redis1ConnectionFactory")RedisConnectionFactory redis1ConnectionFactory){
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redis1ConnectionFactory);
        return template;
    }
}
