package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

public class PageableRestResponse<T extends BaseResult> extends BaseRestResponse{
    @Getter
    @Setter
    private PageResult<T> result;
}
