package com.dino.registermodule.service;

import com.dino.registermodule.base.BaseServices;
import com.dino.registermodule.domain.entity.LoginUser;
import com.dino.registermodule.domain.result.LoginResult;
import org.springframework.transaction.annotation.Transactional;

/**
 *用户正常登陆
 */
@Transactional(readOnly = false)
public interface NormalLoginService extends BaseServices<LoginUser> {
    //邮箱登录
    LoginResult login(String loginMail, String password);

}
