# 198 House-Robber 
 
difficulty: Easy 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and <b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>
<p>Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight <b>without alerting the police</b>.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
&nbsp;            Total amount you can rob = 1 + 3 = 4.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,7,9,3,1]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
&nbsp;            Total amount you can rob = 2 + 9 + 1 = 12.
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 400</code></li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int rob(int[] nums) {
        if( nums.length < 1 ) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++ ) {
            int prePre = i - 2 < 0 ? 0 : dp[i - 2];
            int pre = i - 1 < 0 ? 0 : dp[ i - 1 ];
            dp[i] = Math.max(prePre + nums[i], pre);
        }
        return dp[nums.length - 1];
    }
}
​
/**
index 0 1 2 3 4
value 3 6 9 2 8
dp[0] = nums[0];
dp[1] = Math.max(  nums[1], dp[0] );
dp[2] = Math.max(dp[0] + nums[2], dp[1]);
...
dp[i] = Math.max( dp[i - 2] + nums[i], dp[ i - 1 ] );
*/
​
​
```

当然很轻松就可以化成 O(1) 的。

```java
class Solution {
    public int rob(int[] nums) {
        if( nums.length < 1 ) {
            return 0;
        }
        int prePre = 0;
        int pre = 0;
        for(int i = 0; i < nums.length; i++ ) {
            int temp = pre;
            pre = Math.max( prePre + nums[i], pre);
            prePre = temp;
        }
        return pre;
    }
}
```