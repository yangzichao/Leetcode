# 1190J. Reverse Substrings Between Each Pair of Parentheses

https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/

## Method not the Best;
用栈和Queue结合
顺序入栈，然后遇到右括号出栈到queue里，遇到左括号停止，再把queue中的丢回栈里。
```Java
class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if( c != ')'){
                stack.push(c);
            }else{
                while(stack.peek() != '('){
                    queue.add(stack.pop());
                }
                stack.pop();
                while(!queue.isEmpty()){
                    stack.push(queue.remove());
                }
            }
        }
        String ans = "";
        for(char c : stack){
            ans += String.valueOf(c);
        }
        return ans;
    }
}
```

## Method Best:
https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/383670/JavaC%2B%2BPython-Why-not-O(N)
