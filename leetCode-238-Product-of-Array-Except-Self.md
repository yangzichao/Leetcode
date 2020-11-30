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

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Product of Array Except Self
        // 其实我们不就是想知道，它左边所有数乘积 和它右边的所有数乘积 然后相乘吗
        // 所以我们左一遍获得所有的 prefix 乘积，右一遍获得所有的 suffix 乘积，
        // 再相乘就好了

        int n = nums.length;
        if( n < 1) throw new IllegalArgumentException("null input");
        if( n < 2) return nums;

        int[] suffixProducts = new int[n];
        int[] prefixProducts = new int[n];

        for(int i = 0; i < n; i++) {
            int reverseIndex = n - 1 - i;
            prefixProducts[i] = i == 0 ? 1 : nums[i - 1] * prefixProducts[i - 1];
            suffixProducts[reverseIndex] = i == 0 ? 1 : nums[reverseIndex + 1] * suffixProducts[reverseIndex + 1];
        }

        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = prefixProducts[i] * suffixProducts[i];
        }
        return ans;
    }
}
```

可以精简为一个伪 O(1) space 的答案。

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[ i - 1 ];
        }
        int product = 1;
        for(int i = nums.length - 2; i >= 0; i-- ){
            product *= nums[i + 1];
            ans[i] *= product;
        }
        return ans;
    }
}
```
