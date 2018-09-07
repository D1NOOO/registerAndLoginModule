package com.dino.registermodule.domain.entity;

import com.dino.registermodule.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LoginLog extends BaseEntity {
    private String loginId;
    private Integer loginType;
    private String loginPlatform;
    private String loginIp;
    private Date loginTime;
    private Integer loginFlag;//1-成功 2-用户错误 3-密码错误 4-账号锁定
//    private String loginName;
}
