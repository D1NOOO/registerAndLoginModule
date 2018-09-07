package com.dino.registermodule.domain.entity;

import com.dino.registermodule.base.BaseEntity;
import lombok.Data;

/**
 * 用户影射实体类
 */
@Data
public class LoginRef extends BaseEntity {
    private String loginId;
    private String userId;
}
