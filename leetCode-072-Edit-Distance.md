# 072 Edit-Distance

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
<div><p>Given two words <em>word1</em> and <em>word2</em>, find the minimum number of operations required to convert <em>word1</em> to <em>word2</em>.</p>
<p>You have the following 3 operations permitted on a word:</p>
<ol>
	<li>Insert a character</li>
	<li>Delete a character</li>
	<li>Replace a character</li>
</ol>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> word1 = "horse", word2 = "ros"
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
horse -&gt; rorse (replace 'h' with 'r')
rorse -&gt; rose (remove 'r')
rose -&gt; ros (remove 'e')
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> word1 = "intention", word2 = "execution"
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
intention -&gt; inention (remove 't')
inention -&gt; enention (replace 'i' with 'e')
enention -&gt; exention (replace 'n' with 'x')
exention -&gt; exection (replace 'n' with 'c')
exection -&gt; execution (insert 'u')
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int minDistance(String word1, String word2) {
        // dp[i][j] 记录的是 word1 (0...i) 到 word2(0...j) 的 minmum distance;
        // 举例 horse, ros, 0 的 位置代表空 String ""
        // 
        //       h o r s e
        //     0 1 2 3 4 5
        //   r 1 1 2 2 3 4 
        //   o 2 3 1 2 3 4
        //   s 3 4 2 2 3 4 
        // 定义函数 D(String a, String b) 返回 a, b 的距离。
        // 每个位置看三个位置，上、左、西北。上、左位置意味着 insert, delete 的操作，而西北位置是 replace 的操作。
        // 举个例子，对于 D(2,2) = D("ro", "ho") = 1,  因为 o == o, 所以它的操作数等于子问题 D("r","h") 的结果。
        // 对于 D(3,3) = D("ros", "hor") = 2  , s != r 有三种办法操作,
        // 如果 replace 当前的 s 或者 r, 那么我们的问题就等价于 (2, 2) 即 如何 D("ro", "ho") 的结果 + 1。
        // 如果我们删掉 ros 的 s, 我们能得到 (2,3) 即 D("ro", "hor"), 即 D("ro", "hor") + 1；
        // 如果我们对 ("ros", "ho") 的 ho 添加了一位r 得到 ("ros", "hor"), 那么就是 D("ros", "ho") + 1;
        // 因此：
        // 如果 i, j 字符相同，直接等于 D( i - 1, j - 1);
        // 如果 不同，则取最小的 D( i - 1, j - 1), D( i, j - 1), D( i - 1, j ),
        
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for( int i = 0; i <= m; i++ ) {
            dp[i][0] = i;
        }
        for( int j = 0; j <= n; j++ ) {
            dp[0][j] = j;
        }
        for(int i = 0; i < m; i++ ) {
            for(int j = 0; j < n; j++ ) {
                if( word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }else{
                    dp[i + 1][ j + 1] = Math.min( Math.min(dp[i ][j + 1],dp[i + 1][j ] ),dp[i ][j ] ) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
​
```
