package com.interview.expressionevaluator;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Evaluating infix expression:
 * First verify the expression
 * Then evaluate the value
 */
public class ExpressionEvaluator<T extends Object> {

    public static void main(String args[]) {

        String expression1 = "((2*5)+(6/2))";
        System.out.println("Expression1: " + expression1);
        System.out.println("Verifying Expression1...");
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        if (expressionEvaluator.verifyExpression(expression1)) {
            System.out.println("Result: " + expressionEvaluator.evaluateExpression(expression1));
        }

        String expression2 = "(((2 * 5) - (1 * 2)) / (11 - 9))";
        System.out.println("Expression2: " + expression2);
        System.out.println("Verifying Expression2 ");
        if (expressionEvaluator.verifyExpression(expression2)) {
            System.out.println("Result: " + expressionEvaluator.evaluateExpression(expression2));
        }

    }

    private int evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");
        Vector stack = new Vector<T>();
        int finalResult = 0;
        int top = -1;
        StringTokenizer tokens = new StringTokenizer(expression, "[]{}()/*-+", true);
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            switch (token) {
                case "[":
                case "{":
                case "(":
                case "/":
                case "*":
                case "-":
                case "+":
                    stack.add(token);
                    top++;
                    break;
                case "]":
                case "}":
                case ")":
                    try {
                        int op2 = Integer.valueOf(String.valueOf(stack.remove(top--))) ;
                        String operator = (String) stack.remove(top--);
                        Integer op1 = Integer.valueOf(String.valueOf(stack.remove(top--)));
                        if (!stack.isEmpty()) {
                            stack.remove(top--);
                        }
                        int result = 0;
                        if (operator.equals("/")){
                            result = op1 / op2;
                        } else if (operator.equals("*")) {
                            result = op1 * op2;
                        } else if (operator.equals("-")) {
                            result = op1 - op2;
                        } else if (operator.equals("+")) {
                            result = op1 + op2;
                        }
                        stack.add(result);
                        top++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    break;
                default:
                    if (token.matches("[0-9]+")) {
                        stack.add(token);
                        top++;
                    }
                    break;
            }
        }
        try {
            finalResult = (int) stack.remove(top--);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalResult;
    }

    public boolean verifyExpression(String expression) {
        Vector stack = new Vector<T>();
        int top = -1;
        for (int j = 0; j < expression.length(); j++) {
            char ch = expression.charAt(j);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.add(ch);
                    top++;
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        char stackContent = (char) stack.remove(top--);
                        if ((ch == '}' && stackContent != '{')
                                || (ch == ']' && stackContent != '[')
                                || (ch == ')' && stackContent != '(')) {
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
        if (!stack.isEmpty()) {
            System.out.println("Error: missing right delimiter");
            return false;
        }
        System.out.println("Expression correct.");
        return true;
    }
}
