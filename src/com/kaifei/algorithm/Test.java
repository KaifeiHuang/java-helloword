package com.kaifei.algorithm;

import java.util.Stack;

public class Test {

    public int calculate(String s) {
        return dfs(s, 0)[0];
    }

    private int[] dfs(String s, int index) {
        Stack<Integer> st = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                int[] root = dfs(s, ++i);
                num = root[0];
                i = root[1];
            }
            if (i == s.length() - 1 || (!Character.isDigit(c) && c != ' ')) {
                switch (sign) {
                    case '+':
                        st.push(num);
                        break;
                    case '-':
                        st.push(-num);
                        break;
                    case '*':
                        st.push(num * st.pop());
                        break;
                    case '/':
                        st.push(st.pop() / num);
                        break;
                    default:
                        break;
                }
                sign = c;
                num = 0;
            }
            if (c == ')') {
                return new int[]{sum(st), i};
            }
        }
        return new int[]{sum(st), s.length() - 1};
    }

    private int sum(Stack<Integer> st) {
        int res = 0;
        while (!st.isEmpty()) {
            res += st.pop();
        }
        return res;
    }
}
