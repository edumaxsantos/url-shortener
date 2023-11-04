package com.github.edumaxsantos.urlshortener.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class AppConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis-17200.c300.eu-central-1-1.ec2.cloud.redislabs.com", 17200);
        return new JedisConnectionFactory(config);
    }
}
