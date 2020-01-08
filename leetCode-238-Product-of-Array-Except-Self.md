# 238J. Product of Array Except Self
https://leetcode.com/problems/product-of-array-except-self/

## Method Two Array

1. 这个题说，每一个位置的数，都等于所有的数之和除以自己，但是又不让用除法。
2. 可以把每个位置上应有的乘积分成两部分，一部分是左边所有数的积，一部分是右边所有数的积。
3. 那么每个位置上应有的数就是左边所有数的乘积和右边所有数的乘积之积。
4. 因此我们动态规划，建立了两个数组。


```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];
        L[0] = 1;
        R[nums.length - 1] = 1;
        for(int i = 1; i < nums.length;i++){
            L[i] = nums[i-1]*L[i-1];
        }        
        for(int j = nums.length - 2; j > -1 ; j--){
            R[j] = nums[j + 1] * R[j+1];
        }        
        for(int i = 0; i < nums.length; i++){
            nums[i] = L[i]*R[i];
        }
        return nums;   
    }
}
```