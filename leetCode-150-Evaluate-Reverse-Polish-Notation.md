# 150J. Evaluate Reverse Polish Notation
https://leetcode.com/problems/evaluate-reverse-polish-notation/

## Method 最佳
据说是最基础的应用之一；
原理很简单：
是数字就压入栈，如果有运算符，就出栈上面两位进行运算，并将此部分运算结果压入栈。
O(N) O(N)
```Java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> myStack = new Stack<>();
        int prev = 0, cur = 0;
        for(int i = 0 ; i < tokens.length; i++){
            if(tokens[i].equals("+") ){
                cur = myStack.pop();
                prev = myStack.pop();
                myStack.push(cur + prev);
            }else if(tokens[i].equals("-") ){
                cur = myStack.pop();
                prev = myStack.pop();
                myStack.push(prev - cur);                
            }else if(tokens[i].equals("*")){
                cur = myStack.pop();
                prev = myStack.pop();
                myStack.push(cur * prev);                
            }else if(tokens[i].equals("/")){
                cur = myStack.pop();
                prev = myStack.pop();
                myStack.push(prev/cur);                
            }else{
                myStack.push(Integer.parseInt(tokens[i]));
            }
        }
        return myStack.pop();

    }
}
```
