# 032J. Longest Valid Parentheses
https://leetcode.com/problems/longest-valid-parentheses/

<pre>
在一段合法的子数组中，左右括号数目首先要相同，并且，
左括号数量在任何时候不得少于右括号。
所以唯一会导致长度停止计算的就是如下
以(开头：
(/无 + 最长合法的子数组 + （ / 无
以)开头
)/无 + 最长合法的子数组 + ) /无

那么无论用什么方法，都需要处理这个情况。
</pre>
## Method 1: Stack
```java
class Solution {
    public int longestValidParentheses(String s) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // dummyhead
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if( c == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    //如果pop完栈空了
                    //这里对应的情况是有更多的右括号不再valid了 比如 ))))() 
                    //此时我们直接更新dummyhead的位置。
                    stack.push(i);
                }else{
                    ans = Math.max(ans,i - stack.peek());                    
                }       
            }
        }
        
        return ans;
    }
}
```

## Method 2: 动态规划
<pre>
dp数组是用来储存当前的连续的最长子数组的。
一个正确的子数组，应当以')'结尾。因此碰到左括号'('直接标记0.
如果')'之前
1. 是 ‘(’:
   那么应该有一个暂时的长度为2的合法子数组。
   此时我们还应该它上一个合法的位置是不是也是合法的子数组，如果是的话就加2。
   因此有 dp[i] = (i > 1 ? dp[i-2] : 0) + 2;
2. 是 ‘）’:
   我们就找到最后一个合法的位置之前来判断。
   比如如果i-1处的右括号的dp值是4，那么就意味着在 i - 4 - 1 处的括号需要我们去判断，如果
   它是左括号，应当再 + 2，不然该位就是0;
   因此定义 lastPos = i - dp[i-1] - 1。
   dp[i] = dp[i-1] + 2 + (lastPos > 1 ? dp[lastPos - 1] : 0) ;

综合并简化：
   我们发现
   dp[i] = (i > 1 ? dp[i-2] : 0) + 2; 是
   dp[i] = dp[i-1] + 2 + (lastPos > 1 ? dp[lastPos - 1] : 0) ;
   的特殊情况，因为上面1情况时，dp[i-1] = 0, 而且 lastPos = i - 1。
   所以我们可以把代码写成更紧凑的形式。
   即 总结如下：
   i 的字符是右括号时，如果 lastPos(即：最近一个需要被判断的位置) = i - dp[i-1] - 1, 不为负且该位置是左括号'('时，
    dp[i] = dp[i-1] + 2 + (lastPos > 1 ? dp[lastPos - 1] : 0); 
    ans = Math.max(ans, dp[i]);  
</pre>
```java
class Solution {
    public int longestValidParentheses(String s) {
        if(s.length() < 2) return 0;
        int ans = 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for(int i = 1; i < s.length(); i++){
            char lastc = s.charAt(i-1);
            char c = s.charAt(i);
            if(c == '('){ //碰到左括号直接标记0
               dp[i] = 0; 
            }else{ //碰到右括号
                // 如果前一位是左括号，说明配对成功。
                if(lastc == '('){
                    dp[i] = (i > 1 ? dp[i-2] : 0) + 2;
                }else{
                    int lastPos = i - dp[i-1] - 1;
                    if(lastPos >= 0 && s.charAt(lastPos) == '('){
                        dp[i] = dp[i-1] + 2 + (lastPos > 1 ? dp[lastPos - 1] : 0) ;
                    }  
                } 
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }
}
```
以上代码可以被简化成如下
```java
class Solution {
    public int longestValidParentheses(String s) {
        if(s.length() < 2) return 0;
        int ans = 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for(int i = 1; i < s.length(); i++){
            char lastc = s.charAt(i-1);
            char c = s.charAt(i);
            int lastPos = i - dp[i-1] - 1;
            if( c == ')' && (  lastPos >= 0 && s.charAt(lastPos) == '('  ) ){
                dp[i] = dp[i-1] + 2 + (lastPos > 1 ? dp[lastPos - 1] : 0); 
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

```



## Method 神奇

<pre>
这个方法是将String 从左到右 再 从右到左扫描一遍。
统计左括号的数量left，和右括号的数量right.
先从左往右扫描：
当他们数量相等的时候就记录下此时的长度。
如果右的超过了左的，说明一定不合法了。那么从头开始计数 left, right都归0.
再从右往左扫：
此时左括号和右括号的性质完全相反了。
</pre>

<pre>
核心的问题是为什么这个方法有效呢？
我们可以做一个积分，每一个左括号是1，右括号是-1。
下图中 黑线 是从左往右 红线是从右往左。线的高度就是积分值。
从左往右的时候, 黑线一旦小于0 (right > left)，就一直等于0.
从右往左的时候，红线一旦大于0 (left > right) 就一直等于0。
可以看出，这样以来就把所有隐藏的位置都找到了。
</pre>
<img src = "imgs/LC032A.png" width = "500" />
```java
public class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
```