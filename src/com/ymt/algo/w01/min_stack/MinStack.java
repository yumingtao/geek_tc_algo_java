package com.ymt.algo.w01.min_stack;

import java.util.Stack;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 * 主体思想：
 * 两个栈1和2，栈1存放插入值，栈2存放当前栈中的最小值
 * 当push一个值的时候，栈1存放值，当前值和栈2栈顶值比较，将小的放入栈2的顶部
 * 当pop一个值的时候，栈1出栈，栈2出栈，getMin返回的是栈2的栈顶值
 *
 * @author yumingtao
 * @date 6/20/21 10:33 PM
 */
public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minValStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minValStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

        if (minValStack.isEmpty()) {
            minValStack.push(val);
        } else {
            int min = getMin();
            if (val < min) {
                minValStack.push(val);
            } else {
                minValStack.push(min);
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }

        if (!minValStack.isEmpty()) {
            minValStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minValStack.peek();
    }
}
