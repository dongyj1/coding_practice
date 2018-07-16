package solutions.MinStack;

import java.util.Stack;

/**
 * Created by dyj on 7/14/18.
 *
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.

 */

//-2, 0, -3,
//-2, 0, -2, -3, -3, -3

public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Integer min;
    public MinStack() {
        min = null;
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            min = x;
        } else {
            if (x > min) {
                stack.push(x);
            } else {
                stack.push(min);
                stack.push(x);
                min = x;
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int x = stack.pop();
        if (!stack.isEmpty()) {
            if (x == min) {
                min = stack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        System.out.println(ms.top());
        ms.push(0);
        System.out.println(ms.top());
        System.out.println(ms.getMin());
        ms.push(-3);
        System.out.println(ms.top());
        System.out.println(ms.getMin());
        ms.push(-3);
        System.out.println(ms.top());
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.getMin());

    }
}
