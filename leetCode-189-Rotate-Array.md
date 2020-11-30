# 189J. Rotate Array

观察一个例子就好了

```java
/**
    1 2 3 4 5 6 7 k = 3
    5 6 7 1 2 3 4
*/
```

那么等于先反转数组，然后分别反转 0,k-1 和 k, end。
当然还可以更高级的 不停换位，比如 index:0 -> index:3, index:3 -> index: 6, index:6 -> (6 + k = 3)%7 = index:2;

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int realK = k%nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, realK - 1);
        reverse(nums, realK, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while( start < end ) {
            swap(nums, start++, end--);
        }
    }
    public void swap(int[] nums, int i , int j ) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
