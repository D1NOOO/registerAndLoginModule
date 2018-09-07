package com.dino.registermodule.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BaseRestResponse implements Serializable{
    @Getter
    @Setter
    private String status = RestResponseStatus.SUCCESS;
    @Getter
    @Setter
    private String message = "success";

    public BaseRestResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseRestResponse(){

    }
}
