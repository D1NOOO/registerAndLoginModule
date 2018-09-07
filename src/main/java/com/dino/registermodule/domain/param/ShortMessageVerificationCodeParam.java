package com.dino.registermodule.domain.param;

import com.dino.registermodule.base.BaseParam;
import lombok.Data;

/**
 * 发送短信验证码入参
 */
@Data
public class ShortMessageVerificationCodeParam extends BaseParam {
    private String loginMobile;
    private String shortMessageType;
}
