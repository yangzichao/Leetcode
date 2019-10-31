# 026. Remove Duplicates from Sorted Array


Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.

Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
```Java
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```


## Method 1: Two Pointers/ Iteration
思路：
* 由于是已经排序好的数组。所以不会遇到已经遇到过的数。
* 方法是双指针。一个指针A从头到尾循环一边。一个指针B待在原地和A的值比较，如果不同，那么指针B
先前进一步，然后将A的值存下来。如果值相同就放过这次循环。
* 注意，发现不同值的时候，一定是先移动指针B，再将值存下来。

### java Solution
```Java
class Solution {
    public int removeDuplicates(int[] nums) {
        int length = 0;
        if (nums.length == 0){
            return 0;
        }
        for (int i = 1; i < nums.length; i += 1){
            if (nums[length] != nums[i]){
                length += 1;
                nums[length] = nums[i];
            }
        }
        return length + 1;
    }
}
```

## Method 2:[参见080](leetCode-080-Remove-Duplicates-from-Sorted-Array-II.md)

因此更好的写法是
```Java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 1){
            return nums.length;
        }
        int length = 1;
        for (int i = 1; i < nums.length; i += 1){
            if (nums[length] != nums[i]){
                nums[length++] = nums[i];
            }
        }
        return length;
    }
}
```
