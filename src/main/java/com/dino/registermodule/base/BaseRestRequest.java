package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BaseRestRequest implements Serializable{
    @Getter
    @Setter
    private Long requestTime;
}
