package com.dino.registermodule.exception;

public class BusinessException extends RuntimeException {
    private String messageCode;
    public BusinessException(){

    }
    public BusinessException(String messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
    }
}
