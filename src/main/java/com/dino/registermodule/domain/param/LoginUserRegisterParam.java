package com.dino.registermodule.domain.param;

import lombok.Data;

/**
 * 注册用户入参
 */
@Data
public class LoginUserRegisterParam extends RegisterParam{
    private String loginMail;
    private String password;
}
