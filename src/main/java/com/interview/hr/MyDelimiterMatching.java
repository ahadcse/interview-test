package com.interview.hr;

import com.interview.GenericStack;

/**
 * Created by ahadcse on 2018-01-27.
 */
public class MyDelimiterMatching {

    public static void main(String a[]) {
        MyDelimiterMatching mdm = new MyDelimiterMatching();
        String expression = "{(a+b)*(c+d)}";
        boolean result = mdm.isDelimiterMatching(expression);
        System.out.println(expression + " == " + result);

        expression = "{(a+b)+[x*(c+d)]}";
        result = mdm.isDelimiterMatching(expression);
        System.out.println(expression + " == " + result);

        expression = "{(a+b)+[x*(c+d)}}";
        result = mdm.isDelimiterMatching(expression);
        System.out.println(expression + " == " + result);

    }

    public boolean isDelimiterMatching(String inputExpr) {
        int stackSize = inputExpr.length();
        GenericStack theStack = new GenericStack(stackSize);
        for (int j = 0; j < inputExpr.length(); j++) {
            char ch = inputExpr.charAt(j);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    theStack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!theStack.isStackEmpty()) {
                        char stackContent = 0;
                        try {
                            stackContent = (char) theStack.pop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if ((ch == '}' && stackContent != '{')
                                || (ch == ']' && stackContent != '[') || (ch == ')' && stackContent != '(')) {
                            System.out.println("Mismatch found: " + ch + " at " + j);
                            return false;
                        }
                    } else {
                        System.out.println("Mismatch found: " + ch + " at " + j);
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        if (!theStack.isStackEmpty()) {
            System.out.println("Error: missing right delimiter");
            return false;
        }
        return true;
    }
}