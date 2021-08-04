package com.kaifei.designpatten.creation.simplefactory;

/**
 * The factory utils to create the specified operation class to handle the two number by giving the operation
 */
public class CreateOperationFactory {

    public static Operation createOperation(String opType) {
        switch (opType) {
            case "+":
                return new AddOperation();
            case "-":
                return new MinusOperation();
            case "*":
                return new MultiOperation();
            case "/":
                return new DivisorOperation();
            default:
                break;
        }
        return new Operation();
    }
}
