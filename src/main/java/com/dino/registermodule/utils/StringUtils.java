package com.dino.registermodule.utils;

public final class StringUtils {
    public static boolean isBlank(String value){
        return org.springframework.util.StringUtils.isEmpty(value);
    }
}
