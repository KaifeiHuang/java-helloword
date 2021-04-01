package com.kaifei.rest;

import com.kaifei.oop.User;
import org.junit.Test;

public class ControllerDemo {


    @Test
    public void responseSUCC(){
        Integer retCode = 0;
        String errorMessage = "success";
        RetCode success = RetCode.SUCCESS;
        User user = new User();
        RestResult restResult = new RestResult(success, user);
        System.out.println(restResult);
    }


    @Test
    public void responseFail(){
        Integer code = 10009;

        RetCode retCode = RetCode.PARAMETER_EMPTY;
        retCode.setMessage("request is invalid.");
        retCode.setCode(code);
        User user = new User();
        RestResult restResult = new RestResult(retCode, user);
        System.out.println(restResult);
    }
}
