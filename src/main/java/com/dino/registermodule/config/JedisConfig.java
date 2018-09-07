package com.dino.registermodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Bean
    public JedisPoolConfig poolConfig(PoolConfigProperties configProperties){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(configProperties.getMaxIdle());
        poolConfig.setMaxTotal(configProperties.getMaxTotal());
        poolConfig.setBlockWhenExhausted(configProperties.isBlockWhenExhausted());
        poolConfig.setMaxWaitMillis(configProperties.getMaxWaitMillis());
        poolConfig.setTestOnBorrow(configProperties.isTestOnBorrow());
        return poolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig, ConnectionFactoryProperties factoryProperties){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(factoryProperties.getHostName());
        connectionFactory.setPort(factoryProperties.getPort());
        connectionFactory.setUsePool(factoryProperties.isUsePool());
        connectionFactory.setPoolConfig(poolConfig);
        connectionFactory.setPassword(factoryProperties.getPassword());
        return connectionFactory;
    }

}
