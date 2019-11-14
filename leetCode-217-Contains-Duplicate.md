# 217J. Contains Duplicate
https://leetcode.com/problems/contains-duplicate/
即判断是否数组中有重复的，是垃圾题跳过
219 220 是进阶系列
## Method 最佳
用 Set, 一旦有重复的直接返回true
```Java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> mySet = new HashSet<Integer>();
        for(int n:nums){
            if(mySet.contains(n)){
                return true;
            }
            mySet.add(n);
        }
        return false;
    }
}
```
