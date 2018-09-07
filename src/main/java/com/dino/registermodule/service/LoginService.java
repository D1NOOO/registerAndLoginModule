package com.dino.registermodule.service;

import com.dino.registermodule.base.BaseServices;
import com.dino.registermodule.domain.entity.LoginUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录操作Service
 * @author jiang_nan
 */
@Transactional(readOnly = false)
public interface LoginService extends BaseServices<LoginUser> {
}
