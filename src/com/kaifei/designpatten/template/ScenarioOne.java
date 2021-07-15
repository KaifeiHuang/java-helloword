package com.kaifei.designpatten.template;

public class ScenarioOne extends TemplateMethodHandler {

    /**
     * 将两束除以10再相乘
     *
     * @param number1
     * @param number2
     * @return
     */
    @Override
    protected int multiTwoSum(int number1, int number2) {
        return (number1+number2)/10;
    }

    /**
     * 将两数乘10再加
     *
     * @param number1
     * @param number2
     * @return
     */
    @Override
    protected int addTwoSum(int number1, int number2) {
        return (number1 + number2 + 20);
    }
}
