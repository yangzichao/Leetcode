# 047J. Permutations II
https://leetcode.com/problems/permutations-ii/

## Backtracking 1
这个方法用的跟上个题一样，
但是为了避免重复的元素造成重复的结果，
用了一个map。而且为了避免重复还排了个序。
```java
class Solution {
    private Map<Integer,Integer> numMap;
    private List<List<Integer>> ans;
    private List<Integer> temp;
    private int[] nums;    
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.ans = new LinkedList<>();
        this.temp = new LinkedList<>();
        this.nums = nums;
        this.numMap = new HashMap<>();
        for(int n : nums){
            numMap.put(n,numMap.getOrDefault(n,0) + 1);
        }
        Arrays.sort(nums);
        backtracker(nums.length);
        return ans;
    }
    
    private void backtracker(int index){
        if(index  == 0){
            ans.add(new LinkedList<Integer>(temp));
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(numMap.getOrDefault(nums[i],0) == 0) continue;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            numMap.put(nums[i], numMap.get(nums[i]) - 1 );
            
            backtracker(index - 1);
            
            temp.remove(temp.size() - 1);
            numMap.put(nums[i], numMap.get(nums[i]) + 1 );
        }
    } 
}
```