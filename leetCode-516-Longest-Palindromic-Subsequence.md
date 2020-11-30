# 516 Longest-Palindromic-Subsequence

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
<div><p>Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.</p>
<p><b>Example 1:</b><br>
Input:</p>
<pre>"bbbab"
</pre>
Output:
<pre>4
</pre>
One possible longest palindromic subsequence is "bbbb".
<p>&nbsp;</p>
<p><b>Example 2:</b><br>
Input:</p>
<pre>"cbbd"
</pre>
Output:
<pre>2
</pre>
One possible longest palindromic subsequence is "bb".
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int longestPalindromeSubseq(String s) {
        // 参见 1312
        int N = s.length();
        int[][] dp = new int[N + 1][ N + 1];
        for(int i = 0; i < N; i++ ) {
            for(int j = 0; j < N; j++ ) {
                int end = N - 1 - j;
                if( s.charAt(i) == s.charAt(end) ) {
                    dp[i + 1][j+ 1] = dp[i][j] + 1;
                }else{
                    dp[i + 1][j+ 1] = Math.max(dp[i ][j+ 1],dp[i + 1][j]);
                }
            }
        }
        return dp[N][N];
    }
} 
​
```
