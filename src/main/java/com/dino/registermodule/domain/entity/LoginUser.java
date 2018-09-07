package com.dino.registermodule.domain.entity;

import com.dino.registermodule.base.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
/**
 * 用户登录实体类
 */
public class LoginUser extends BaseEntity {
    private String loginName;
    private String loginMobile;
    private String loginMail;
    private String password;
    private String loginStatus;
    private Date lastLoginTime;
    private String lastLoginIp;
    private String lastLoginDevice;
    private Date registerTime;
    private String loginUserType;
    private String userId;
    private LoginRef loginRef;
    private List<ThirdPartAccount> thirdPartAccountList;
}
