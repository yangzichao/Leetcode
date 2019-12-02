# 334J. Increasing Triplet Subsequence

https://leetcode.com/problems/increasing-triplet-subsequence/

## Method Best: O(N) O(1)
<pre>
注意题目要求的是
不一定连续，只要数组里有 0 < i < j < k < n - 1 就行。
因此用两个指针，如果取了两个顺序的数字，后续还有比他俩更大的，
那么就返回 true.
</pre>
```Java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int medium = Integer.MAX_VALUE;
        for(int n : nums){
            if(n <= small){
                small = n;
            }else if (n <= medium){
                medium = n;
            }else{
                return true;
            }
        }
        return false;
    }
}
```
