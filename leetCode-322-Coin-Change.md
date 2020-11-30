# 322 Coin-Change 
 
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
<div><p>You are given coins of different denominations and a total amount of money <i>amount</i>. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return <code>-1</code>.</p>
<p><b>Example 1:</b></p>
<pre><strong>Input: </strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>
<strong>Output: </strong><code>3</code> 
<strong>Explanation:</strong> 11 = 5 + 5 + 1</pre>
<p><b>Example 2:</b></p>
<pre><strong>Input: </strong>coins = <code>[2]</code>, amount = <code>3</code>
<strong>Output: </strong>-1
</pre>
<p><b>Note</b>:<br>
You may assume that you have an infinite number of each kind of coin.</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    // top-down method
    // dp(N) = min( dp(N - a), dp(N - b), ... all possibilities) + 1;
    // dp(0) = 0;
    // dp(negative values) = -1, 不考虑它
    public int coinChange(int[] coins, int amount) {
        if(amount == 0){
            return 0;
        }
        return helper(coins, amount, new int[amount]);
    }
    public int helper(int[] coins, int amount, int[] counts ){
        if( amount < 0 ){
            return -1;
        }
        if( amount == 0 ){
            return 0;
        }
        // 这个 early return 是 dp 的关键之处
        if( counts[amount - 1] != 0){
            return counts[amount - 1];
        }
        int min = Integer.MAX_VALUE;
        for(int coin : coins ){
            int cur = helper( coins, amount - coin, counts);
            if( cur >= 0){
                min = Math.min(min, cur);
            }
        }
        counts[amount - 1] = min == Integer.MAX_VALUE ? -1 : min + 1;
        return counts[amount - 1];
    }
}
​
```