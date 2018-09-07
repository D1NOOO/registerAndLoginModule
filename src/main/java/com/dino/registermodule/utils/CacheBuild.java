package com.dino.registermodule.utils;


import com.dino.registermodule.config.RedisComponent;

import java.util.Map;

public final class CacheBuild {
    private static Map<CacheType, CacheManager> CACHE_MANAGER_MAP;
    private static RedisComponent REDIS_COMPONENT;

    public CacheBuild() {

    }


    public enum CacheType {
        MAIL_VERIFICATION_CODE_TYPE,
        SHOT_MESSAGE_VERIFICATION_CODE_TYPE;
    }


    public static Cache build(CacheType cacheType) {
        CacheManager cacheManager = CACHE_MANAGER_MAP.get(cacheType);
        Cache cache = new Cache(cacheManager, REDIS_COMPONENT);
        return cache;
    }

    public void setCacheManagerMap(Map<CacheType, CacheManager> cacheManagerMap) {
        CACHE_MANAGER_MAP = cacheManagerMap;
    }

    public void setRedisComponent(RedisComponent redisComponent) {
        REDIS_COMPONENT = redisComponent;
    }
}
