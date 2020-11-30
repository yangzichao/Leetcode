# 224 Basic-Calculator 
 
difficulty: Hard 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Implement a basic calculator to evaluate a simple expression string.</p>
<p>The expression string may contain open <code>(</code> and closing parentheses <code>)</code>, the plus <code>+</code> or minus sign <code>-</code>, <b>non-negative</b> integers and empty spaces <code> </code>.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> "1 + 1"
<strong>Output:</strong> 2
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> " 2-1 + 2 "
<strong>Output:</strong> 3</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> "(1+(4+5+2)-3)+(6+8)"
<strong>Output:</strong> 23</pre>
<b>Note:</b>
<ul>
	<li>You may assume that the given expression is always valid.</li>
	<li><b>Do not</b> use the <code>eval</code> built-in library function.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int calculate(String s) {
        Queue<Character> tokens = new ArrayDeque<>();
        for( char c : s.toCharArray() ) {
            if( c == ' ') {
                continue;
            }
            tokens.offer(c);
        }
        tokens.offer('t');
        return evaluate(tokens);
    }
​
    public int evaluate( Queue<Character> tokens ) {
        int sum = 0;
        int preNum = 0;
        char preOp = '+';
        int num = 0;
        
        while(  !tokens.isEmpty() ) {
            char c = tokens.poll();
            if( '0' <= c && c <= '9' ) {
                num = num*10 + (c - '0');
            }else if( c == '(') {
                num = evaluate(tokens);
            }else{
                switch(preOp) {
                    case '+':
                        sum += preNum;
                        preNum = num;
                        break;
                    case '-':
                        sum += preNum;
                        preNum = -num;
                        break;
                    case '*':
                        preNum *= num;
                        break;
                    case '/':
                        preNum /= num;
                        break;
                }
                if( c == ')') {
                    break;
                }
                preOp = c;
                num = 0;
            }
        }
        return sum + preNum;
    }
}
​
```