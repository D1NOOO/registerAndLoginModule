package com.dino.registermodule.config;

public abstract class PoolConfigProperties {

    public abstract int getMaxIdle();

    public abstract int getMaxTotal();

    public abstract boolean isBlockWhenExhausted();

    public abstract int getMaxWaitMillis();

    public abstract boolean isTestOnBorrow();
}
