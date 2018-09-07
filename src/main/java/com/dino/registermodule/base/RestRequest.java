package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

public class RestRequest<T extends BaseParam> extends BaseRestRequest{
    @Getter
    @Setter
    private T param;
}
