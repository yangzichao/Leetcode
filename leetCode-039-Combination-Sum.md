# 039J. Combination Sum
https://leetcode.com/problems/combination-sum/

这个题的难点是 动态规划 DP.c
## Method Trivial: BackTracking

这个题BackTracking的难度在于，又一个思维的惯性。
一般backtracker(int index), 自动，index + 1。
而这个题不排除使用重复的元素。
如 [2,3,6,7] 找 7.
如何既找到 [2，2，3], 又避免出现重复的结果，如[2,3,2] [3,2,2] 呢？
方法是 向下传递的时候 index 并不加 1. 中止思维惯性。

而 target - nums[i], 也较为巧妙。


```java
class Solution {
    private int[] nums;
    private List<List<Integer>> ans;
    private LinkedList<Integer> temp;
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.nums = candidates;
        this.ans = new LinkedList<>();
        this.temp = new LinkedList<>();
        backtracker(target, 0);
        return ans;
    }
    private void backtracker(int target, int index){
        if(target < 0) return;
        if(target == 0){
            ans.add(new LinkedList<>(temp));
            return;
        }
        for(int i = index ; i < nums.length; i++){
            temp.addLast(nums[i]);
            backtracker(target - nums[i], i);
            temp.removeLast();
        }
    }
}
```