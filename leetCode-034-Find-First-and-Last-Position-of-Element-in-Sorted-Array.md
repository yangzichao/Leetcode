# 034J. Find First and Last Position of Element in Sorted Array
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


## Method Good: Traditional Binary Search
日后慢慢分析一下
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};
        int[] firstLast = new int[2];
        int left = 0;
        int right = nums.length - 1;
        
        while(left < right){
            int pivot = left + (right - left)/2;
            if(nums[pivot] < target){
                left = left + 1;
            }else{
                right = right - 1;
            }
        }

        firstLast[0] = nums[left] == target ? left: -1;
        
        left = 0;
        right = nums.length - 1;
        int pivot = left + (right - left)/2;
        while(left < right){
            pivot = left + (right - left)/2 + 1;
            if(nums[pivot] > target){
                right = right - 1;
            }else{
                left = left + 1;
            }
        } 
        firstLast[1] = nums[right] == target ? right: -1;
        return firstLast;
    }
}
```