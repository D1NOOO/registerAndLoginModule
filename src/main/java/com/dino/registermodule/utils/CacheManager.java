package com.dino.registermodule.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheManager {

    private Cache<String, Object> cache;

    public CacheManager() {
        this.cache = CacheBuilder.newBuilder().build();
    }

    public CacheManager(RemovalListener<String, Object> removalListener) {
        this.cache = CacheBuilder.newBuilder().removalListener(removalListener).build();
    }

    public CacheManager(RemovalListener<String, Object> removalListener, long effectiveTime){
        this.cache = CacheBuilder.newBuilder().expireAfterWrite(effectiveTime, TimeUnit.MILLISECONDS).removalListener(removalListener).build();
    }


    public void saveCache(String key, Object value) {
        this.cache.put(key, value);
    }

    public void batchSaveCache(Map<String, Object> cacheMapValue) {
        this.cache.putAll(cacheMapValue);
    }

    public void remove(String key) {
        this.cache.invalidate(key);
    }

    public void removeAll(List<String> keyList) {
        this.cache.invalidateAll(keyList);
    }

    public void removeAll() {
        this.cache.invalidateAll();
    }

    public Object getValue(String key) {
       return null;
    }

}
