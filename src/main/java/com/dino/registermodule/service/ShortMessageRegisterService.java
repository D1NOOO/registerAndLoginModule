package com.dino.registermodule.service;

import com.dino.registermodule.domain.result.LoginUserRegisterResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 短信注册接口
 */
@Transactional(readOnly = false)
public interface ShortMessageRegisterService extends RegisterService{
    //手机短信注册
    LoginUserRegisterResult register(String loginMobile, String verificationCode);

}
