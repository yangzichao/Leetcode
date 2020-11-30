# 213 House-Robber-II 
 
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
<div><p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are <strong>arranged in a circle.</strong> That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and&nbsp;<b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>
<p>Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight <strong>without alerting the police</strong>.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> [2,3,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
&nbsp;            because they are adjacent houses.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
&nbsp;            Total amount you can rob = 1 + 3 = 4.</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if( nums.length == 1 ) {
            return nums[0];
        }
        int max1 = simpleRob(nums, 0, nums.length - 1);
        int max2 = simpleRob(nums, 1, nums.length);
        return Math.max( max1, max2 );
    }
    public int simpleRob(int[] nums, int start, int end) {
        if( nums.length < 1 ) {
            return 0;
        }
        int prePre = 0;
        int pre = 0;
        for(int i = start; i < end; i++ ) {
            int temp = pre;
            pre = Math.max( prePre + nums[i], pre);
            prePre = temp;
        }
        return pre;
    }
}
​
/**
你不能同时抢首尾两个房子。
所以问题变成了，从 0 到 n-1 和 1 - n 两种抢法的最大收益的比较。
*/
​
​
​
```