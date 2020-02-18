package com.interview.practice;

@FunctionalInterface
public interface TestFuncInt<From, To> {
    To transform(From f);
}
