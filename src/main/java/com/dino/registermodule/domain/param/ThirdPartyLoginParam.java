package com.dino.registermodule.domain.param;

import lombok.Data;

/**
 * 第三方登录入参
 */
@Data
public class ThirdPartyLoginParam extends LoginParam{
    private String openId;
    private String thirdPartType;
}
