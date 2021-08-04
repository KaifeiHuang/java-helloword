package com.kaifei.designpatten.creation.simplefactory;

import org.junit.Assert;
import org.junit.Test;

public class Client {


    @Test
    public void testAddOperationWithSimpleFactory(){
        // given
        String opType = "+";
        Operation operation = CreateOperationFactory.createOperation(opType);
        operation.number1 = 10d;
        operation.number2 = 20d;

        // when
        double result = operation.getResult();

        // then
        Assert.assertTrue(Double.valueOf(30).compareTo(Double.valueOf(result))==0);
    }

    @Test
    public void testMinusOperationWithSimpleFactory(){
        // given
        String opType = "-";
        Operation operation = CreateOperationFactory.createOperation(opType);
        operation.number1 = 10d;
        operation.number2 = 20d;

        // when
        double result = operation.getResult();

        // then
        Assert.assertTrue(Double.valueOf(-10).compareTo(Double.valueOf(result))==0);
    }
}
