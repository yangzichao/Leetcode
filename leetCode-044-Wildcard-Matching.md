# 044 Wildcard-Matching 
 
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
<div><p>Given an input string (<code>s</code>) and a pattern (<code>p</code>), implement wildcard pattern matching with support for <code>'?'</code> and <code>'*'</code>.</p>
<pre>'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
</pre>
<p>The matching should cover the <strong>entire</strong> input string (not partial).</p>
<p><strong>Note:</strong></p>
<ul>
	<li><code>s</code>&nbsp;could be empty and contains only lowercase letters <code>a-z</code>.</li>
	<li><code>p</code> could be empty and contains only lowercase letters <code>a-z</code>, and characters like <code><font face="monospace">?</font></code>&nbsp;or&nbsp;<code>*</code>.</li>
</ul>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong>
s = "aa"
p = "a"
<strong>Output:</strong> false
<strong>Explanation:</strong> "a" does not match the entire string "aa".
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong>
s = "aa"
p = "*"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;'*' matches any sequence.
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong>
s = "cb"
p = "?a"
<strong>Output:</strong> false
<strong>Explanation:</strong>&nbsp;'?' matches 'c', but the second letter is 'a', which does not match 'b'.
</pre>
<p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong>
s = "adceb"
p = "*a*b"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
</pre>
<p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong>
s = "acdcb"
p = "a*c?b"
<strong>Output:</strong> false
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int j = 0; j < n; j++ ) {
            if( p.charAt(j) == '*') {
                dp[0][j + 1] = true; 
            }else{
                break;
            }
        }
        
        for(int i = 0; i < m; i++ ) {
            for( int j = 0; j < n; j++ ) {
                char sc = s.charAt(i);
                char pc = p.charAt(j);
                if( equals(sc, pc) ) {
                    dp[i + 1][ j+ 1] = dp[i][j];
                    continue;
                }
                if( pc != '*') {
                    continue;
                }
                dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j];
            }
        }
        return dp[m][n];
    }
    public boolean equals( char s, char p) {
        return s == p || p == '?';
    }
    
}
​
/**
if sc === pc // === 定义是 a == b || b == ?;
如果 sc === pc 那么显然是 2d dp 各减少1
即 dp[i + 1][j + 1] = dp[ i ][ j ];
continue;
​
如果 sc !== pc;
如果 pc != * 直接说明 dp[i + 1][j + 1] = false;
​
如果 pc == *，
'*' 有两个操作，一是灭别人一位并且保留自己，
    即 dp[i + 1][j + 1] = dp[ i ][ j + 1];
    二是消灭自己，什么都不匹配，
    即 dp[i + 1][j + 1] = dp[ i + 1 ][ j ];
    
然后处理边界条件
空串匹配空串还是有用的 0,0 = true
p 空 s 非空都是 false;
s 空的话，p 只能是一串 ******
*/
​
```