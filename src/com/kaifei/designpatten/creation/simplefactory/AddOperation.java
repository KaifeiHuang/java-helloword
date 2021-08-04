package com.kaifei.designpatten.creation.simplefactory;

public class AddOperation extends Operation {
    @Override
    public double getResult() {
        return  super.number1+super.number2;
    }
}
