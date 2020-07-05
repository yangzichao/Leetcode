# 091 Decode-Ways 
 
difficulty: Medium 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>A message containing letters from <code>A-Z</code> is being encoded to numbers using the following mapping:</p>
<pre>'A' -&gt; 1
'B' -&gt; 2
...
'Z' -&gt; 26
</pre>
<p>Given a <strong>non-empty</strong> string containing only digits, determine the total number of ways to decode it.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> "12"
<strong>Output:</strong> 2
<strong>Explanation:</strong>&nbsp;It could be decoded as "AB" (1 2) or "L" (12).
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> "226"
<strong>Output:</strong> 3
<strong>Explanation:</strong>&nbsp;It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int numDecodings(String s) {
        // 这个和之前的一样，主要是思想转不过来
        // 对于第 N 个字符，想要到达它，只能通过第 N - 1 和 第 N - 2 个字符。
        // 若通过 N - 1 到达 N ，解码方式只能把 N 单独解码。那么 dp[N] 加上 dp[N - 1];
        // 若通过 N - 2 到达 N ，解码方式只能把 N 和 N - 1 一起解码，那么 dp[N] 再加上 dp[N - 2];
        // 比如 987654321，到达1，可以通过 98765432，也可以通过 9876543 
        // 这个题还有0就比较恶心。因为如果有单独的 0，例如 909，那么总数直接是0.
        // 但是如果0可以和10 20 结合，那么 像 1020101 就可以有结合的方式，总数并不是0。
​
        //这个方法是最省空间的，但是处理edge cases 就很棘手。
        if(s.length() < 1) {
            return 0;
        }
​
        int prev2 = 1; // 第 1 个，作为辅助开始
        int prev  = s.charAt(0) == '0' ? 0:1; // 真实的第一个
        if(s.length() < 2) {
            return prev;
        }
        
        int cur = 0;
        for(int i = 1 ; i < s.length() ; i++ ) {
            int one = Integer.parseInt( s.substring(i, i + 1) );
            int two = Integer.parseInt( s.substring(i - 1, i + 1) );
            if( one == 0 ) {
                cur = 0;
            }
            if( one > 0 ) {
                cur = prev;
            }
            if( two > 9 && two < 27) {
                cur += prev2;
            }
            
            prev2 = prev;
            prev = cur;
        }
        return cur;        
    }
}
​
```