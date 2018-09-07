package com.dino.registermodule.service;

import com.dino.registermodule.domain.result.LoginResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 短信登录接口
 */
@Transactional(readOnly = false)
public interface ShortMessageLoginService extends LoginService{
    //手机短信登录
    LoginResult login(String loginMobile, String verificationCode);
}
