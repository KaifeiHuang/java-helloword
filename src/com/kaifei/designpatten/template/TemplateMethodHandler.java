package com.kaifei.designpatten.template;

public abstract class TemplateMethodHandler {

    /**
     * 模板方法： 先跟据规则进行计算，计算完成后将结果汇总
     *
     * @param number1
     * @param number2
     * @return
     */
    public final int sumTemplateMethod(int number1, int number2) {
        // 1. 两数根据规则求和
        int addRes = addTwoSum(number1, number2);

        // 2.两数根据规则求积
        int multiRes = multiTwoSum(number1, number2);

        // 3.对步骤1和步骤2求和
        int totalRes = addRes + multiRes;
        return totalRes;
    }

    /**
     * 特定算法的步骤
     *
     * @param number1
     * @param number2
     * @return
     */
    protected abstract int multiTwoSum(int number1, int number2);

    /**
     * 特定算法的步骤
     *
     * @param number1
     * @param number2
     * @return
     */
    protected abstract int addTwoSum(int number1, int number2);

}
