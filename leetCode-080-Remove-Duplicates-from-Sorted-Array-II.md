# 080J. Remove Duplicates from Sorted Array II
Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}


## Method Best
思路是这样的，双指针。如果数组长度小于 2，直接返回原数组length.
size是慢指针，i是快指针。一开始就指向第三位。并且size始终指向已经确认无误位置的下一位。
只要快指针所指的值，和size-2处不同，那么就取快指针的值给nums[size]。注意是size-2处。
如果相同意味着已经有两个这种数了。    
* [1(size - 2),1,1(i,size),2,2,3] 开始 由于 size - 2 和 i 相等，没有操作 i++    
* [1(size - 2),1,1(size),2(i),2,3] size - 2 < i 的值，i 的值 赋给 size           
* [1,1(size - 2),2,2(size),2(i),3] size - 2 < i 的值，i 的值 赋给 size           
* [1,1,2(size - 2),2,2(size),3(i)] size - 2 < i 的值，i 的值 赋给 size           
* [1,1,2,2(size - 2),3,3(size)] i超出，循环停止，此时size = 5 是正确的。       
```java
public int removeDuplicates(int[] nums) {
    if(nums.length < 2+1){
        return nums.length;
    }
    int size = 2;
    for(int i = 2; i < nums.length; i++){
        if(nums[i] > nums[size-2]){
            nums[size++] = nums[i];
        }
    }
    return size;
}
```
for k:
```Java
public int removeDuplicates(int[] nums) {
    if(nums.length < k+1){
        return nums.length;
    }
    int size = k;
    for(int i = k; i < nums.length; i++){
        if(nums[i] > nums[size-k]){
            nums[size++] = nums[i];
        }
    }
    return size;
}
```
concise:
```java
public int removeDuplicates(int[] nums) {
    int size = 0;
    for(int n : nums){
      if(size < 2 || n > nums[size - 2]){
        nums[size++]= n;
      }
    }
    return size;
}
```
