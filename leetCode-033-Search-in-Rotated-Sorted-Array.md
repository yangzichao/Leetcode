# 033J. Search in Rotated Sorted Array

https://leetcode.com/problems/search-in-rotated-sorted-array/

## Method Best:

这个题看似不可以强行 Binary Search, 实际上还是可以直接 Binary Search 的。
只不过需要多加个判断条件。这是因为可以利用转动后的数组，仍然可以判断 target
在左侧还是在右侧。

```Java
class Solution {
    public int search(int[] nums, int target) {
        if(nums.length < 1){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int pivot;
        while(left<=right){
            pivot = left + (right - left)/2;
            if(nums[pivot] == target){
                return pivot;
            }
            // 判断条件也可以是 >= nums[left] 效果一样，但是要把所有的都换了
            if(nums[pivot] >= nums[0] ){ // 说明左侧有序
                if( nums[pivot] >= target && target >= nums[0]){
                    //如果在有序的这侧，即左侧
                    right = pivot - 1;
                }else{
                    //反之
                    left = pivot + 1;
                }
            }else{ //说明右侧有序
                //如果在有序的这侧，即右侧
                if(nums[pivot] <= target && target <= nums[nums.length-1]){
                    left = pivot + 1;
                }else{
                    right = pivot - 1;
                }
            }
        }
        return -1;
    }
}
```

这是一个换掉了条件的例子

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int pivot = left + (right - left )/ 2;
            if(nums[pivot] == target ) {
                return pivot;
            }
            if(nums[left] <= nums[pivot]) {
                // left side is ordered
                // 如果在有序的这侧
                if( nums[pivot] >= target &&target >= nums[left]  ) {
                    right = pivot - 1;
                }else{
                    left = pivot + 1;
                }
            }else {
                // right side is ordered
                if( nums[right] >= target &&  target >= nums[pivot] ) {
                    left = pivot + 1;
                }else{
                    right = pivot - 1;
                }
            }
        }
        return - 1;
    }
}
```
