package com.dino.registermodule.service.serviceImpl;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.dao.LoginShortMessageDao;
import com.dino.registermodule.domain.entity.LoginShortMessage;
import com.dino.registermodule.service.LoginShortMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 手机短信登录
 */
@Service
public class LoginShortMessageServiceImpl implements LoginShortMessageService {
    @Autowired
    private LoginShortMessageDao loginShortMessageDao;
    @Override
    public BaseDao<LoginShortMessage> getBaseDao() {
        return loginShortMessageDao;
    }
}
