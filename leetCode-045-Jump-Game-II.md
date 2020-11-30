# 045 Jump-Game-II 
 
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
<div><p>Given an array of non-negative integers, you are initially positioned at the first index of the array.</p>
<p>Each element in the array represents your maximum jump length at that position.</p>
<p>Your goal is to reach the last index in the minimum number of jumps.</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> [2,3,1,1,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.</pre>
<p><strong>Note:</strong></p>
<p>You can assume that you can always reach the last index.</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    /*
    https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
    最高赞下面确实好多魔鬼
    为何 i < nums.length - 1 ?
    因为我们不关心到达最后一个位置之后再怎么跳，如果 nums.length, 我们很多时候会多跳一次才停止。
    这个解法本身是一个 BFS。
    我们 i 到 curEnd 之间搜寻最大可能的向右跳的范围 curFartherest,
    一旦 i == curEnd，说明我们搜索完毕，然后我们假设跳到了 curFatherest, step+=1 。
    但是这不意味着全局来看，跳到 curFatherest 就是最好的解
    我们继续在 i 和 新的 curEnd 之间继续搜寻下一跳的最大的 curFatherest。
    */
    public int jump(int[] nums) {    
        int curEnd = 0;
        int curFartherest = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            curFartherest = Math.max(curFartherest, i+nums[i]);
            if( curFartherest >= nums.length - 1){
                return steps + 1;
            }
            if( i == curEnd ){
                steps += 1;
                curEnd  = curFartherest;
            }
        }
        return steps;
    }
}
​
​
/**
下面的是一个 O(N^2)的解法，我觉得还挺好的，然而太慢
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0; i < nums.length; i++ ){
            int maxStep = nums[i];
            for(int next = maxStep; next >= 1; next--){
                if( i + next >= nums.length ){
                    continue;
                }
                if( dp[ i + next ]  < 0 ){
                    dp[ i + next ] = dp[i] + 1;
                }else{
                    dp[ i + next ] = Math.min( dp[ i + next ], dp[i] + 1);
                }
                if( i + next == nums.length ){
                    return dp[nums.length - 1];
                }
            }
        }
        return dp[nums.length - 1];
    }
*/
​
```