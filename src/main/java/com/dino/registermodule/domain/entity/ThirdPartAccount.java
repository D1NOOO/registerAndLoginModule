package com.dino.registermodule.domain.entity;

import com.dino.registermodule.base.BaseEntity;
import lombok.Data;

/**
 * 第三方登录实体类
 */
@Data
public class ThirdPartAccount extends BaseEntity {
    private String loginId;
    private String openId;
    private String thirdPartyAccountType;
}
