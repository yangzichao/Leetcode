# 279 Perfect-Squares 
 
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
<div><p>Given a positive integer <i>n</i>, find the least number of perfect square numbers (for example, <code>1, 4, 9, 16, ...</code>) which sum to <i>n</i>.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> <i>n</i> = <code>12</code>
<b>Output:</b> 3 
<strong>Explanation: </strong><code>12 = 4 + 4 + 4.</code></pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> <i>n</i> = <code>13</code>
<b>Output:</b> 2
<strong>Explanation: </strong><code>13 = 4 + 9.</code></pre></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int numSquares(int n) {
        //  dp[n] 是以 n 为结尾的最少的个数。
        //  确实是个经典的 dp 套路
        //  复杂度是 N \sqrt{N};
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++ ) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            while( j*j <= i ) {
                min = Math.min( dp[ i - j*j ] + 1, min);
                j++;
            }
            dp[i] = min;
        }
        return dp[ n ];
    }
}
​
```