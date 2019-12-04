# 035J. Search Insert Position

https://leetcode.com/problems/search-insert-position/

## Method: Best Binary Search
Easy.
但是需要想一想返回left的道理。
一般来说Binary Search不返回 pivot.
最好把等于的情况判给right.
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int pivot = 0;
        while(left <= right){
            pivot = left + (right - left)/2;
            if(nums[pivot] < target){
                left = pivot + 1;
            }else{
                right = pivot - 1;
                // if target in nums.
                // Pivot would be stayed at the left side of the target, return left;
                // if target not in nums.
                // return left as well.
            }
        }
        return left;
    }
}
```
