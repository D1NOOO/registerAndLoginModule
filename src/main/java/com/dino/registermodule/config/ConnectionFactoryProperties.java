package com.dino.registermodule.config;

public abstract class ConnectionFactoryProperties {
    public abstract String getHostName();
    public abstract int getPort();
    public abstract boolean isUsePool();
    public abstract String getPassword();
}
