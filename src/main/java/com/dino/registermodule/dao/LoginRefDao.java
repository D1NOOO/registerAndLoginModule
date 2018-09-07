package com.dino.registermodule.dao;

import com.dino.registermodule.base.BaseDao;
import com.dino.registermodule.domain.entity.LoginRef;
import org.apache.ibatis.annotations.Param;

public interface LoginRefDao extends BaseDao<LoginRef> {
    //通过登录Id查找用户影射表的信息
    LoginRef selectLoginRefByLoginId(@Param("loginId") String loginId);
}
