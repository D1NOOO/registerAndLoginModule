package com.dino.registermodule.service;

import com.dino.registermodule.base.BaseServices;
import com.dino.registermodule.domain.entity.LoginUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户注册
 */
@Transactional(readOnly = false)
public interface RegisterService extends BaseServices<LoginUser> {
    //根据登录名查找用户是否存在
    boolean checkLoginName(String loginName);
}
