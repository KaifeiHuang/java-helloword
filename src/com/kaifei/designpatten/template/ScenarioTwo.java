package com.kaifei.designpatten.template;

public class ScenarioTwo extends TemplateMethodHandler {

    /**
     * 将两束除以100再相乘
     *
     * @param number1
     * @param number2
     * @return
     */
    @Override
    protected int multiTwoSum(int number1, int number2) {
        return (number1+number2)/100;
    }

    /**
     * 将两数乘100再加
     *
     * @param number1
     * @param number2
     * @return
     */
    @Override
    protected int addTwoSum(int number1, int number2) {
        return Integer.valueOf(number1 + number2 + 200);
    }
}
