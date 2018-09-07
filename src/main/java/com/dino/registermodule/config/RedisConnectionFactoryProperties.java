package com.dino.registermodule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConnectionFactoryProperties extends ConnectionFactoryProperties {
    @Value("${redis.hostName}")
    private String hostName;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.usePool}")
    private boolean usePool;
    @Value("${redis.password}")
    private String password;

    @Override
    public String getHostName() {
        return hostName;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isUsePool() {
        return usePool;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
