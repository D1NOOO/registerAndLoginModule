package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

public class OrderFieldParam extends BaseParam{
    @Getter
    @Setter
    private String fieldName;
    @Getter
    @Setter
    private String orderType;
}
