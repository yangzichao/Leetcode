# 027. Remove Element
Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeElement(nums, val);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}


## 解法1 (保持其余的顺序)
逆向思维，不等于val就赋值。
快慢双指针。用数组的size当作慢指针，另一个遍历的快指针i。
快指针只要遇到不需要被排除的树，慢指针就把他记录下来，然后慢指针才移动，否则不动。
这种方法保持了其他不被删除的元素的初始顺序。
T O(N), S O(1)
```Java
class Solution {
    public int removeElement(int[] nums, int val) {
        int size = 0;
        for(int i = 0; i< nums.length; i++){
            if(nums[i]!=val){
                nums[size] = nums[i];
                size++;
            }
        }
        return size;
    }
}
```
## 解法2 (不在乎顺序，效率更高)
这次是正向思维，等于val就移除。
两个指针，一个从前往后，一个从后往前。后指针代表着处理后的size.
如果前指针遇到了等于val的，那么就把后指针指的数取走，后指针向前移动。
如果后指针也是等于val并且给了前指针，没关系，前指针只有不等于val的时候才会移动，
等于val只移动后指针。这样在需要被移除的元素不多的情况下，效率更高。

```Java
public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            n--;
        } else {
            i++;
        }
    }
    return n;
}
```
