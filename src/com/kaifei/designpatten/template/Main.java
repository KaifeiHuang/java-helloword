package com.kaifei.designpatten.template;

public class Main {
    public static void main(String[] args) {
        TemplateMethodHandler templateMethodHandler = new ScenarioOne();
        int res1 = templateMethodHandler.sumTemplateMethod(10, 20);
        System.out.println(res1);

        TemplateMethodHandler templateMethodHandler1 = new ScenarioTwo();
        int res2 = templateMethodHandler1.sumTemplateMethod(20, 10);
        System.out.println(res2);

    }
}

