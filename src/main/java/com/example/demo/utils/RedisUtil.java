package com.example.demo.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/*
*  redis工具类
*/
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;
    
    public void setString(String key, String value){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        //默认一天过期时间
        valueOperations.set(key, value,1,TimeUnit.DAYS);
    }

    /**
     * @param key
     * @param value
     * @param time  过期时间 单位:秒
     */
    public void setString(String key, Object value,int time){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value,time, TimeUnit.SECONDS);
    }
    /**
     * get redis: string类型
     * @param key key
     * @return
     */
    public String getString(String key){
        if (redisTemplate.opsForValue().get(key) == null)
            return null;
        return (String) redisTemplate.opsForValue().get(key);
    }

    /**
     *  
     * @param key
     * @return  返回  -1为空
     */
    public int getInt(String key){
        if (redisTemplate.opsForValue().get(key) == null)
            return -1;
        return (int) redisTemplate.opsForValue().get(key);
    }

    public Object getObject(String key){
        if (redisTemplate.opsForValue().get(key) == null)
            return -1;
        return  redisTemplate.opsForValue().get(key);
    }


}
