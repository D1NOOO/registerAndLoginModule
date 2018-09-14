package com.dino.registermodule.domain.param;

import com.dino.registermodule.base.BaseParam;
import lombok.Data;

/**
 * 手机找回密码入参
 */
@Data
public class ShortMessageForgetPasswordParam extends BaseParam {
    private String loginMobile;
    private String verificationCode;
    private String newPassword;
}
