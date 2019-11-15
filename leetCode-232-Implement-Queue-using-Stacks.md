# 232J. Implement Queue using Stacks
https://leetcode.com/problems/implement-queue-using-stacks/

## Method Best: Two Stacks
<pre>
原理是用两个stack, 一个入栈 一个出栈；
对于 push, 所有的push都push到input里面
对于 pop和peek，原理相同：
如果 output 栈是空的，那么就把input全部倒入output里，
然后从output里peek 或者 pop. 如果output不是空的，那么
就直接从 output 里面 peek pop。所以要注意，一定要等
output是空的时候才能pop peek。想一想也很有道理，不难。
</pre>

```Java
class MyQueue {
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(output.empty()){
           while(!input.empty()){
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.empty() && output.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```
