package com.dino.registermodule.controller;


import com.dino.registermodule.base.BaseRestResponse;
import com.dino.registermodule.base.RestResponse;

public abstract class BaseRestController {

    protected <T> RestResponse<T> createRestResponse(T result) {
        RestResponse<T> response = new RestResponse<>();
        response.setResult(result);
        return response;
    }

    protected BaseRestResponse createRestResponse(){
        return new BaseRestResponse();
    }
}
