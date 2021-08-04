package com.kaifei.designpatten.creation.simplefactory;

public class DivisorOperation extends Operation {
    @Override
    public double getResult() {
        return super.number1 / super.number2;
    }
}
