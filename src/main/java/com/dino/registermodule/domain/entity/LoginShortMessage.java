package com.dino.registermodule.domain.entity;

import com.dino.registermodule.base.BaseEntity;
import lombok.Data;

@Data
public class LoginShortMessage extends BaseEntity {
    private String loginId;
    private String loginMobile;
    private String verificationCode;
    private String status;
    private long sendDateTime;
    private long endDateTime;
    private String shortMessageType;
}
