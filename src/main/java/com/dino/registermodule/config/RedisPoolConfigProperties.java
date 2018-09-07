package com.dino.registermodule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisPoolConfigProperties extends PoolConfigProperties {
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.blockWhenExhausted}")
    private boolean blockWhenExhausted;
    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    @Override
    public int getMaxTotal() {
        return maxTotal;
    }

    @Override
    public boolean isBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    @Override
    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    @Override
    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }
}
