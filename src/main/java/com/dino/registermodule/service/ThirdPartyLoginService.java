package com.dino.registermodule.service;

import com.dino.registermodule.base.BaseServices;
import com.dino.registermodule.domain.entity.ThirdPartAccount;
import com.dino.registermodule.domain.result.LoginResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方登录
 */
@Transactional(readOnly = false)
public interface ThirdPartyLoginService extends BaseServices<ThirdPartAccount> {
    //第三方登录
    LoginResult login(String openId, String thirdPartAccountType);
}
