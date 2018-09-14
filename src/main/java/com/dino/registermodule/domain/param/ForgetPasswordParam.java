package com.dino.registermodule.domain.param;

import com.dino.registermodule.base.BaseParam;
import lombok.Data;

/**
 * 忘记密码入参
 */
@Data
public class ForgetPasswordParam extends BaseParam {
    private String loginMail;
    private String verificationCode;
    private String newPassword;
}
