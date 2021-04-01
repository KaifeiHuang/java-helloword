package com.kaifei.rest;

public enum RetCode {

    SUCCESS(0, "Successful"),  // 此处message应该用中英文国际化处理
    PARAMETER_EMPTY(1001, "parameter is empty"); // 此处message应该用中英文国际化处理

    private Integer code;

    private String message;

    RetCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
