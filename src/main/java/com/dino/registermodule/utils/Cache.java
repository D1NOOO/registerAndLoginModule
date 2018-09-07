package com.dino.registermodule.utils;

import com.dino.registermodule.config.RedisComponent;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

public final class Cache {

    private CacheManager cacheManager;

    private RedisComponent redisComponent;

    public Cache(CacheManager cacheManager, RedisComponent redisComponent) {
        this.cacheManager = cacheManager;
        this.redisComponent = redisComponent;
    }

    public void save(String key, String value, long effectiveTime) {
        cacheManager.saveCache(key, value);
        this.redisComponent.set(key, value, effectiveTime);
    }

    public void save(String key, Map<String,String> value,long effectiveTime){
        cacheManager.saveCache(key,value);
        this.redisComponent.set(key, value, effectiveTime);
    }

    public String getValue(String key){
        String value = (String) this.cacheManager.getValue(key);
        if(StringUtils.isEmpty(value)){
            value = redisComponent.get(key);
        }
        return value;
    }

    public Map<String,Object> getMapValue(String key){
        Map<String,Object> value = (Map<String,Object>)this.cacheManager.getValue(key);
        if(CollectionUtils.isEmpty(value)){
            value = redisComponent.getMap(key);
        }
        return value;
    }

    public void remove(String key){
        this.cacheManager.remove(key);
        this.redisComponent.remove(key);
    }
}
