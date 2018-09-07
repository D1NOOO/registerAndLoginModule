package com.dino.registermodule.domain.result;

import com.dino.registermodule.base.BaseResult;
import lombok.Data;

/**
 * 用户注册返回值
 */
@Data
public class LoginUserRegisterResult extends BaseResult {
    private String loginId;
    private String userId;
}
