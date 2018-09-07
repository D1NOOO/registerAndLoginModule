package com.dino.registermodule.service;

import com.dino.registermodule.domain.result.LoginUserRegisterResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户正常注册
 */
@Transactional(readOnly = false)
public interface NormalRegisterService extends RegisterService{
    //邮箱注册
    LoginUserRegisterResult register(String loginMail, String password);

}
