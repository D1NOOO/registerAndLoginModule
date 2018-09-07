package com.dino.registermodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class RedisComponent {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    private static String redisCode = "utf-8";

    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    public void set(String key, Map<String, String> value, long effectiveTime) {
        this.redisTemplate.expire(key, effectiveTime, TimeUnit.SECONDS);
        hashOperations.putAll(key, value);
    }

    public Map<String, Object> getMap(String key) {
        return this.hashOperations.entries(key);
    }

    public void remove(final String key) {
        this.redisTemplate.delete(key);
    }

    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] value = connection.get(key.getBytes());
                    if(value==null){
                        return null;
                    }
                    return new String(value, redisCode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "";
            }
        });
    }
}
