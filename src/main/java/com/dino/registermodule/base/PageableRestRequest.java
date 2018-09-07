package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PageableRestRequest<T extends BaseParam> extends RestRequest<T>{
    @Setter
    @Getter
    private PageParam pageParam;
    @Setter
    @Getter
    private List<OrderFieldParam> orderFieldParamList;
    @Setter
    @Getter
    private T param;

}
