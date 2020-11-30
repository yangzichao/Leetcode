# 772 Basic-Calculator-III 
 
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
<p>The expression string may contain open <code>(</code> and closing parentheses <code>)</code>, the plus <code>+</code> or minus sign <code>-</code>, <strong>non-negative</strong> integers and empty spaces <code> </code>.</p>
<p>The expression string contains only non-negative integers, <code>+</code>, <code>-</code>, <code>*</code>, <code>/</code> operators , open <code>(</code> and closing parentheses <code>)</code> and empty spaces <code> </code>. The integer division should truncate toward zero.</p>
<p>You may assume that the given expression is always valid. All intermediate results will be in the range of <code>[-2147483648, 2147483647]</code>.</p>
<p>Some examples:</p>
<pre>"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong> <strong>Do not</strong> use the <code>eval</code> built-in library function.</p>
</div></section>
 
 ## Method One 
 
``` Java

class Solution {
    public int calculate(String s) {
       Queue<Character> tokens = new ArrayDeque<Character>();
​
       for(char c : s.toCharArray()){
           if(c != ' ') tokens.offer(c);
       }
​
       tokens.offer('t'); // offer dummyTail 让最后一个字符也能被处理
       return calculate(tokens);
    }
    
    private int calculate(Queue<Character> tokens){
        /** 
        有三类字符，1是数字，2是括号，3是运算符。
        对字符串处理，我们的 notations 是这样的: sum +  preNum preOp num  c，
        sum 就是之前所有运算的结果, preNum preOp num 是我们最近邻的一个完整的表达式， c 是我们目前处理的字符。
        如果我们碰到的 c 是数字，说明我们需要更新 num.
        如果 c 是左括号，说明 c 前面是 preOp, 我们开始 dfs 直到 右括号结束，我们把整个括号内的结果作为num.
        如果 c 是右括号，意味着运算终止，此时我们要先处理 sum +  preNum preOp num 再打断循环并向上返回结果。
        如果 c 是一个运算符，那么我们也要立刻处理 sum +  preNum preOp num
            如果 preOp 是 +,-, => sum = sum +  preNum; preNum = +,- num; preOp = c.
            如果 preOp 是 *,/, => sum = sum; preNum = preNum *,/ num; preOp = c.
        因此运算结束的时候，我们会始终保留一个 sum + preNum, 因此需要返回 sum + preNum. 
        */
        int sum = 0;
        int preNum = 0;
        char preOp = '+';
        int num = 0;
        while(!tokens.isEmpty()) {
            char c = tokens.poll();
        
            if('0' <= c && c <= '9') {
                num = num * 10 + ( c - '0' );
            }else if( c == '(' ) {
                num = calculate(tokens);
            }else {
                switch (preOp){
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
                
                if( c == ')' ) {
                    break;
                }
                preOp = c;
                num = 0;
            }
       }
​
       return sum + preNum;
    }
}
​
​
```