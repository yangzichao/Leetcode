# 034J. Find First and Last Position of Element in Sorted Array

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

## Method Good: Traditional Binary Search

日后慢慢分析一下。一定要好好学啊，这可是二项搜索之王一通百通。

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};
        int[] range = new int[2];

        int totalNumber = nums.length;
        int left = 0;
        int right = totalNumber - 1;
        while(left < right) {
            int pivot = left + (right - left)/2;
            int num = nums[pivot];
            if(num < target) {
                left = pivot + 1;
            }else{
                right = pivot;
            }
        }

        range[0] = nums[left] == target ? left : -1;

        left = 0;
        right = totalNumber - 1;
        while(left < right) {
            int pivot = left + (right - left + 1)/2;
            int num = nums[pivot];
            if(num > target){
                right = pivot - 1;
            }else{
                left = pivot;
            }
        }
        range[1] = nums[right] == target ? right : -1;

        return range;
    }
}
```
