# 046J. Permutations
https://leetcode.com/problems/permutations/


## Backtracking 1 
这个题是一个简单的backtracking 
但是实现permutation有两种方法
我实现的方法就是用穷举排列组合。这个需要利用一个额外的set。
因为种种原因，稍微慢一点。
还有一种方法就是真的去permutation，相对快。
这种情况下的思路类似于
第一层 
123
每一层下面都是
123，然后删掉用过的。

代码中的index代表的是层数。层数到0说明可以记录了。
也可以反过来从0开始，意义一样。
```java
class Solution {
    private Set<Integer> used;
    private List<List<Integer>> ans;
    private List<Integer> temp;
    private int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        this.ans = new LinkedList<>();
        this.temp = new LinkedList<>();
        this.nums = nums;
        this.used = new HashSet<Integer>();
        backtracker(nums.length);
        return ans;
    }
    
    private void backtracker(int index){
        if(index  == 0){
            ans.add(new LinkedList<Integer>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used.contains(nums[i])) continue;
            temp.add(nums[i]);
            used.add(nums[i]);
            
            backtracker(index - 1);
            
            temp.remove(temp.size() - 1);
            used.remove(nums[i]);
        }
    }
}
```

原来List也实现了contains的接口。所以可以写的更简单一点。

```java
class Solution {
    private List<List<Integer>> ans;
    private List<Integer> temp;
    private int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        this.ans = new LinkedList<>();
        this.temp = new LinkedList<>();
        this.nums = nums;
        backtracker(nums.length);
        return ans;
    }
    
    private void backtracker(int index){
        if(index  == 0){
            ans.add(new LinkedList<Integer>(temp));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(temp.contains(nums[i])) continue;
            temp.add(nums[i]);       
            backtracker(index - 1);      
            temp.remove(temp.size() - 1);
        }
    }
}
```

## Backtracking 2

思路就是对于原数组，
在第一层，我们依次把每一个元素交换到到第一位来。
然后进入下一层。
在第二层就是依次把剩下的元素交换到第二位来。
然后进入下下层。
回溯将数组复原。

```java
class Solution {
    private List<List<Integer>> ans;
    private List<Integer> nums;
    public List<List<Integer>> permute(int[] nums) {
        this.ans = new LinkedList<>();
        this.nums = new ArrayList<>();
        for(int n : nums){
            this.nums.add(n);
        }
        backtracker(0);
        return ans;
    }
    
    private void backtracker(int index){
        if(index  == nums.size()){
            ans.add(new ArrayList<Integer>(nums));
            return;
        }
        for(int i = index; i < nums.size(); i++){
            Collections.swap(nums, index, i);
            backtracker(index + 1);
            Collections.swap(nums, index, i);
        }
    }
}
```
