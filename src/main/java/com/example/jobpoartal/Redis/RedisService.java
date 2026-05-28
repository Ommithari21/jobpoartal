package com.example.jobpoartal.Redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j // This automatically creates the 'log' instance variable. Do not add LoggerFactory manually.
public class RedisService {

    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);

    public <T> T get(String key, Class<T> type){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(o, type);
        } catch (Exception e) {
            log.error("Error retrieving key from Redis: {}", key, e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttl) {
        try {
            // Added TimeUnit.SECONDS so your TTL functions properly
            redisTemplate.opsForValue().set(key, o, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Error saving key to Redis: {}", key, e);
        }
    }
}
