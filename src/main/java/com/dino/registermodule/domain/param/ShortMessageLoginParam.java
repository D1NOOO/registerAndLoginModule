package com.dino.registermodule.domain.param;

import lombok.Data;

/**
 * 手机登录入参
 */
@Data
public class ShortMessageLoginParam extends LoginParam{
    private String loginMobile;
    private String verificationCode;
}
