package com.dino.registermodule.domain.result;

import com.dino.registermodule.base.BaseResult;
import lombok.Data;

@Data
public class ThirdPartAccountResult extends BaseResult {
    private String openId;
    private String thirdPartyAccountType;
    private String id;
}
