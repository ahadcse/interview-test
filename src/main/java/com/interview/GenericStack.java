package com.interview;

public class GenericStack<T extends Object> {

    private T[] stack;
    private int size;
    private int top;

    public GenericStack(int size) {
        this.size = size;
        this.stack = (T[]) new Object[size];
        this.top = -1;
    }

    public void push(T item) {
        if(isStackFull()) {
            System.out.println("Stack full. Increasing capacity");
            increaseStackCapacity();
        }
        System.out.println("Pushing item: " + (stack[++top] = item));
    }

    public T pop() throws Exception {
        if(isStackEmpty()) {
            throw new Exception("Stack Empty");
        }
        T item = this.stack[top--];
        System.out.println("Popping item: " + (item));
        return item;
    }

    public void printStack() {
        System.out.println("Printing stack...");
        for(T item : this.stack) {
            if(item != null)System.out.println(item);
        }
    }

    private void increaseStackCapacity() {
        T[] newStack = (T[]) new Object[this.size * 2];
        System.arraycopy(this.stack, 0, newStack, 0, stack.length - 1);
        this.stack = newStack;
        this.size = this.size * 2;
    }

    public boolean isStackEmpty() {
        return top == -1;
    }

    public boolean isStackFull() {
        return top == size - 1;
    }

    public static void main(String arg[]) {
        GenericStack<String> stringGenericStack = new GenericStack<>(2);
        stringGenericStack.push("Test push 1");
        stringGenericStack.push("Test push 2");
        try {
            stringGenericStack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringGenericStack.printStack();
        GenericStack<Integer> integerGenericStack = new GenericStack<>(3);
        integerGenericStack.push(5);
        integerGenericStack.push(8);
        integerGenericStack.push(9);
        try {
            integerGenericStack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        integerGenericStack.printStack();
    }
}
