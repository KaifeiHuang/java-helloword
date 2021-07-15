package com.kaifei.alibaba;

import com.sun.xml.internal.ws.util.StringUtils;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class InterviewDemo {

    public int calculateStrExpr(String s) {
        if (null == s || "".equals(s)) {
            throw new IllegalArgumentException("Invalid string expression");
        }

        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        // 每次将计算的值放入stack中
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {

                num = num * 10 + ch - '0';
            }

            if (!Character.isDigit(ch) && ch !=' ' || i == len-1) {
                switch (preSign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop()*num);
                        break;
                    default:
                        stack.push(stack.pop()/num);
                }

                preSign = ch;
                num=0; //遇到符合重新统计数字
            }
        }

        // 计算栈中元素的和
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    @Test
    public void testCalculator(){
        String s = "1+2*3-4/2";
        int res = calculateStrExpr(s);
        System.out.println(res);

    }

    @Test
    public void testCalculator1(){
        System.out.println(10 + '9' - '0');

    }



}
