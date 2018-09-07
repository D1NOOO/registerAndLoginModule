package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

public class RestResponse<T> extends BaseRestResponse{
    @Getter
    @Setter
    private T result;
}
