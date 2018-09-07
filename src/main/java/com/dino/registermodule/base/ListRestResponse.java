package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ListRestResponse<T> extends BaseRestResponse{
    @Getter
    @Setter
    private List<T> itemList;
}
