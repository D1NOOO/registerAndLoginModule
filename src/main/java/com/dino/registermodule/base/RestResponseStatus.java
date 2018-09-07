package com.dino.registermodule.base;

public interface RestResponseStatus {
    /**操作成功*/
    String SUCCESS = HttpStatus.OK;
    /**操作失败*/
    String ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    /**未登录*/
    String NOT_LOGIN = HttpStatus.UNAUTHORIZED;
}
