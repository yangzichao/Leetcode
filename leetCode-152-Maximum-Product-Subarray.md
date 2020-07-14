# 152 Maximum-Product-Subarray

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
<div><p>Given an integer array&nbsp;<code>nums</code>, find the contiguous subarray within an array (containing at least one number) which has the largest product.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> [2,3,-2,4]
<strong>Output:</strong> <code>6</code>
<strong>Explanation:</strong>&nbsp;[2,3] has the largest product 6.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> [-2,0,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;The result cannot be 2, because [-2,-1] is not a subarray.</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int maxProduct(int[] nums) {
        // 对于连乘这个题，有如下的观察
        // 数组中的 0，相当于把数组分成了好几个子数组来运算，因为一旦出现乘0，所有的都变0了。
        // 因此我们现在只考虑一个没有0 的子数组。
        // 子数组中的负数，如果是偶数个，那么最大的乘积就是整个子数组。
        // 如果是奇数个，比如说有 (2k + 1) 个负数。那么必然可以分成两个子数组
        // 一个子数组是第一个负数左边的数组，另一个是第一个负数右边的子数组，有2k个负数，因此是正数。
        // 因此，最大的乘积，一定是一个prefix或者suffix。
        int lProduct = 0;
        int rProduct = 0;
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++) {
            // 这里 lProduct == 0 和 rProduct == 0 就是为了检查是不是出现了0，如果出现了就重设当前乘积为1。
            lProduct = ( lProduct == 0 ? 1 : lProduct ) * nums[i];
            rProduct = ( rProduct == 0 ? 1 : rProduct ) * nums[nums.length - i - 1];
            max = Math.max(max, Math.max(lProduct, rProduct));
        }
        return max;
    }
}
​
```
