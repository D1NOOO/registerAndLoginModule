package com.dino.registermodule.domain.param;

import lombok.Data;

/**
 * 手机注册入参
 */
@Data
public class ShortMessageRegisterParam extends RegisterParam{
    private String loginMobile;
    private String verificationCode;
}
