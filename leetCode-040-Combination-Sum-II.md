# 040J. Combination Sum II

https://leetcode.com/problems/combination-sum-ii/

？是说考虑一下DFS的解法

## Method BackTracking:
<pre>
这个题和上个题一模一样，
唯一的难点是如何排除重复的答案？
一个朴素的想法，
比如[1,2,1,1,1,1,1]
我们先排序，因为回溯实际上是暴力法，这个排序的代价不算什么。
[1,1,1,1,1,1,2]
对于这个问题，我们实际想要的是：
每个元素在每一层递归中，只使用一次。

因此写出了这个条件。
if( i > index && nums[i] == nums[i - 1]) continue;
</pre>

```java
class Solution {
    private List<List<Integer>> ans;
    private List<Integer> temp;
    private int[] nums;
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.ans = new LinkedList<>();
        this.temp = new LinkedList<>();
        this.nums = candidates;
        Arrays.sort(nums);
        backtracker(target, 0);
        return ans;
    }
    
    private void backtracker(int target, int index){
        if(target < 0) return;
        if(target == 0){
            ans.add(new LinkedList<Integer>(temp));
            return;
        }
        for(int i = index; i < nums.length; i++){
            if( i > index && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            backtracker(target - nums[i], i + 1);
            temp.remove(temp.size() - 1);
        }
        return;
    }
}
```

## Method 2: DFS
这个题也可以用DFS来解答

因为我们不想使用重复的元素，所以如果我们能够用图一样来标记是否访问过，
就可以避免使用重复的元素。