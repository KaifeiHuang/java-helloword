package com.kaifei.rest;

import java.io.Serializable;

public class RestResult implements Serializable {

    /**
     * 业务层retCode
     * 从1000开始，应该常用公共retCode区分开
     */
    private Integer retCode;

    /**
     * 异常信息
     * 默认为： ”“， 有异常时显示异常信息
     */
    private String errorMessage;

    /**
     * 响应body
     */
    private Object data;

    public RestResult(RetCode retCode, Object data) {
        this.retCode = retCode.code();
        this.errorMessage = retCode.message();
        this.data = data;
    }


    public static RestResult success(Object data){
        RestResult restResult = new RestResult();
        restResult.setRetCode(RetCode.SUCCESS.code());
        restResult.setData(data);
        return restResult;
    }

    public static RestResult failure(RetCode retCode, Object data){
        RestResult restResult = new RestResult();
        restResult.setRetCode(retCode.code());
        restResult.setErrorMessage(retCode.message());
        restResult.setData(data);
        return restResult;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "retCode=" + retCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }

    public RestResult() {
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
