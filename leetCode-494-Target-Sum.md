# 494 Target-Sum 
 
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
<div><p>You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols <code>+</code> and <code>-</code>. For each integer, you should choose one from <code>+</code> and <code>-</code> as its new symbol.</p>
<p>Find out how many ways to assign symbols to make sum of integers equal to target S.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> nums is [1, 1, 1, 1, 1], S is 3. 
<b>Output:</b> 5
<b>Explanation:</b> 
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
There are 5 ways to assign symbols to make the sum of nums be target 3.
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li>The length of the given array is positive and will not exceed 20.</li>
	<li>The sum of elements in the given array will not exceed 1000.</li>
	<li>Your output answer is guaranteed to be fitted in a 32-bit integer.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int maxAbsSum = 0;
        for(int n : nums){
            maxAbsSum += n; // 
        }
        if( Math.abs(S) > maxAbsSum ){
            return 0;
        }
        /** 由于输入不会超过 1000 所以我们可以只用一个 dp 数组 如果真的面试此题，需要沟通这个输入
         这是个 0/1 Knapsack Problem. 
         For each number in the array, we could either choose to add the number to
         a current sum or minus the number.
         Thus for a specific sum, we are able to receive two contributions from the
         previous sub-problems.
         Let me write down the state-transition equation here:
         dp[i][ curSum ] = dp[ i - 1 ][ curSum - nums[i - 1] ] + dp[ i - 1 ][ curSum - nums[i + 1] ];
         这个问题，由于我们始终需要选择所有的数，所以我们不需要 2D 数组，只需要两个 1D 就行。
         因为之所以我们有这个 i 在这里，是因为0/1 knapsack 问题本来是决定要不要取这个数，这里我们变成了正负号的取舍。
        */
        // int[][] dp = new int[nums.length + 1][ 2*maxAbsSum + 1];
        // dp[0][maxAbsSum] = 1;
        // for(int i = 1; i <= nums.length; i++){
        //     for(int j = -maxAbsSum; j <= maxAbsSum; j++ ){
        //         int index = j + maxAbsSum;
        //         if( index - nums[i - 1] >= 0 ){
        //             dp[i][index] += dp[i - 1][ index - nums[i - 1] ];
        //         }
        //         if( index + nums[i - 1] <= 2*maxAbsSum){
        //             dp[i][index] += dp[i - 1][ index + nums[i - 1] ];
        //         }
        //         // dp[i][index] = dp[i - 1][ index - nums[i - 1] ] + dp[ i - 1 ][ index + nums[i - 1] ]
        //     }
        // }
        // return dp[nums.length][S + maxAbsSum];
​
        // 这是1D的答案
        int[] dp = new int[ 2*maxAbsSum + 1];
        dp[maxAbsSum] = 1;
        for(int i = 0; i < nums.length; i++){
            int[] next = new int[ 2*maxAbsSum + 1];
            for(int j = -maxAbsSum; j <= maxAbsSum; j++ ){
                int index = j + maxAbsSum;
                if( index - nums[i] >= 0 ){
                    next[index] += dp[ index - nums[i] ];
                }
                if( index + nums[i] <= 2*maxAbsSum){
                    next[index] += dp[ index + nums[i] ];
                }
            }
            dp = next;
        }
        return dp[S + maxAbsSum];
    }
}​
```