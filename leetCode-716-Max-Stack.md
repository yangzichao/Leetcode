# 716J?. Max Stack

## Method: Two Stack
这个方法相对好想一点，其实可以挑战从deque开始的方法。
```Java
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> stackMax;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<Integer>();
        stackMax = new Stack<Integer>();
    }

    public void push(int x) {
        if(stack.isEmpty()){
            stack.add(x);
            stackMax.add(x);
        }else{
            if(x > peekMax()){
                stackMax.add(x);
            }else{
                stackMax.add(peekMax());
            }
            stack.add(x);
        }
    }

    public int pop() {
        stackMax.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return stackMax.peek();
    }

    public int popMax() {
        int max = stackMax.peek();
        Stack<Integer> buffer = new Stack<>();
        while(stack.peek()!=max){
            buffer.push(pop());
            // buffer.push(stack.pop());
            // stackMax.pop();
        }
        pop();
        // stack.pop();
        // stackMax.pop();
        while(!buffer.isEmpty()){
            push(buffer.pop());
            // int x = buffer.pop();
            // if(stack.isEmpty()){
            //     stack.add(x);
            //     stackMax.add(x);
            // }else{
            //     if(x > peekMax()){
            //         stackMax.add(x);
            //     }else{
            //         stackMax.add(peekMax());
            //     }
            //     stack.add(x);
            // }            
        }
        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
```
