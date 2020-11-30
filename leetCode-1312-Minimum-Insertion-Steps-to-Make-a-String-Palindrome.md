# 1312 Minimum-Insertion-Steps-to-Make-a-String-Palindrome

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
<div><p>Given a string <code>s</code>. In one step you can insert any character at any index of the string.</p>
<p>Return <em>the minimum number of steps</em> to make <code>s</code>&nbsp;palindrome.</p>
<p>A&nbsp;<b>Palindrome String</b>&nbsp;is one that reads the same backward as well as forward.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "zzazz"
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string "zzazz" is already palindrome we don't need any insertions.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "mbadm"
<strong>Output:</strong> 2
<strong>Explanation:</strong> String can be "mbdadbm" or "mdbabdm".
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "leetcode"
<strong>Output:</strong> 5
<strong>Explanation:</strong> Inserting 5 characters the string becomes "leetcodocteel".
</pre>
<p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> s = "g"
<strong>Output:</strong> 0
</pre>
<p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong> s = "no"
<strong>Output:</strong> 1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li>All characters of <code>s</code>&nbsp;are lower case English letters.</li>
</ul></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int minInsertions(String s) {
        // 参见 1143 516 它 和 1143 516 是一样的题目
        // 1143 是两个 string 找 最长 subsequence 
        //
        // 对于 1312, 如果我们把这个 String 反过来
        // 比如 zzaz, 反过来是 zazz, 我们找到它最长的 subsequence 的长度 k = 3，
        // 那么我们就只需要插入 N - k = 1 个就可以让  zzaz 和 zazz 相同了。
        // 论证:
        // 一个 palindrome String 和 它的 reverse string 的 最长 subsequence 肯定是他们自己。
        // 一个 String 和 它 reverse String 的 subsequence 肯定是 回文 palindrome 的。
        // 剩下的字母我们都可以通过在对应位置复制一份实现回文。每添加一个字符，就能减少一个不回文的字符。
        // 假设一个 string p 是 palindrome. 令 p 分成两份 p = pa + pb;
        // 现在添加一个字符 a 构成新 String 是 pa + a + pb; 
        // 记pa,pb 的reverse 是 ap, bp. ap + bp = p;
        // 那么reverse String 是 bp + a + ap. 最长 subsequence 还是 p.
        // 为了消除 a, 那么就需要在对应位置加一个 a 就行了。
        
        int N = s.length();
        int[][] dp = new int[N + 1][N + 1];
        
        for(int i = 0;i < N; i++ ) {
            for(int j = 0; j < N; j++ ) {
                int end = N - 1 - j;
                if( s.charAt(i) == s.charAt(end) ) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }else {
                    dp[i + 1][j + 1] = Math.max(dp[i ][j + 1] ,dp[i + 1][j] );
                }
            }
        }
        return N - dp[N][N];
    }
}
​
```
