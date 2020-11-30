# 115 Distinct-Subsequences 
 
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
<div><p>Given a string <strong>S</strong> and a string <strong>T</strong>, count the number of distinct subsequences of <strong>S</strong> which equals <strong>T</strong>.</p>
<p>A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, <code>"ACE"</code> is a subsequence of <code>"ABCDE"</code> while <code>"AEC"</code> is not).</p>
<p>It's guaranteed the answer fits on a 32-bit signed integer.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input: </strong>S = <code>"rabbbit"</code>, T = <code>"rabbit"
<strong>Output:</strong>&nbsp;3
</code><strong>Explanation:</strong>
As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)
<code>rabbbit</code>
^^^^ ^^
<code>rabbbit</code>
^^ ^^^^
<code>rabbbit</code>
^^^ ^^^
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input: </strong>S = <code>"babgbag"</code>, T = <code>"bag"
<strong>Output:</strong>&nbsp;5
</code><strong>Explanation:</strong>
As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)
<code>babgbag</code>
^^ ^
<code>babgbag</code>
^^    ^
<code>babgbag</code>
^    ^^
<code>babgbag</code>
  ^  ^^
<code>babgbag</code>
    ^^^
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int numDistinct(String s, String t) {
        // t 是 Pattern, 我选择让他竖着
        // s 是横着的。
        // 所以第一行是 1，因为 t 是 "". 
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int j = 0; j < n; j++ ) {
            dp[0][j] = 1;
        }
        
        for(int i = 0; i < m; i++ ) {
           for(int j = i; j < n; j++ ) {
               // 注意这个 j = i, 其实无所谓，但是这意味着，
               // 如果 t 比 s 短，一定得到的是个 0. 
               // 第 i + 1, j + 1 收到两个贡献。
               // 一个是来自 i + 1, j 的，无论 s.charAt(j) == t.charAt(i)
               // 我们都有这个。比如 s = rabc, t = rab, 天然就等于 s = rab, t = rab 的结果。因为 t 已经用完了。
               // 如果 s = rabb, t = rab， 我们还能加上一个来自 s = rab, t = ra， 因为我们成功给这一组续命了。
               // 如果 不是 b == b， 我们就没法算上这个方向的贡献。
               dp[ i + 1 ][ j + 1 ] = dp[ i + 1][ j  ];
               if( s.charAt(j) == t.charAt(i) ){
                   dp[ i + 1][j + 1 ] += dp[i][j];
               }
           } 
        }
        return dp[m][n];
    }
}
​
```