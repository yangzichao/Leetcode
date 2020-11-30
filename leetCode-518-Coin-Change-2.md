# 518 Coin-Change-2 
 
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
<div><p>You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.</p>
<ul>
</ul>
<p>&nbsp;</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> amount = 5, coins = [1, 2, 5]
<b>Output:</b> 4
<b>Explanation:</b> there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
</pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> amount = 3, coins = [2]
<b>Output:</b> 0
<b>Explanation:</b> the amount of 3 cannot be made up just with coins of 2.
</pre>
<p><b>Example 3:</b></p>
<pre><b>Input:</b> amount = 10, coins = [10] 
<b>Output:</b> 1
</pre>
<p>&nbsp;</p>
<p><b>Note:</b></p>
<p>You can assume that</p>
<ul>
	<li>0 &lt;= amount &lt;= 5000</li>
	<li>1 &lt;= coin &lt;= 5000</li>
	<li>the number of coins is less than 500</li>
	<li>the answer is guaranteed to fit into signed 32-bit integer</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    // 这个题超级有意思。
    // 怪不得和这个 coin change 不一样
    // 相当于，我们先 dp 只用一种 coin;
    // 然后 再加上一种 coin 更新
    // 这样的话留下的就只是组合。
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin: coins ){
            for(int i = coin; i <= amount; i++){
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
​
​
/**
The fun part about this solution is that if you switch the order of the for loops in the code, your answer almost doubles! Learnt it the hard way.
​
To make an amount of 3 with two coin denominations 1 and 2, you can go:
1 + 2 = 3
2 + 1 = 3
Both mean the same thing, in this question.
​
所以下面的是错的。
​
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 1; i <= amount; i++ ){
            for(int coin : coins ){
                if( i - coin < 0 ){
                    continue;
                }
                dp[i] += dp[i - coin];
            }       
        }
        return dp[amount];
    }
*/
​
```