package com.dino.registermodule.domain.param;

import com.dino.registermodule.base.BaseParam;
import lombok.Data;

/**
 * 重置密码入参
 */
@Data
public class ResetPasswordParam extends BaseParam {
    private String loginId;
    private String newPassword;
}
