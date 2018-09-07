package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class ListRestRequest<T extends Serializable> extends BaseRestRequest{
    @Getter
    @Setter
    private List<T> paramList;
}
