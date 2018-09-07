package com.dino.registermodule.domain.param;

import lombok.Getter;
import lombok.Setter;

/**
 * 正常登陆入参
 */
@Getter
@Setter
public class NormalLoginParam extends LoginParam{
    private String loginName;
    private String password;
}
