package com.kaifei.rest.exception;

public class MercuryException extends RuntimeException{

    Integer status;
    private String errorMessage;

    public MercuryException(String message) {
        super(message);
    }
}
