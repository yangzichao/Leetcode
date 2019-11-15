# 136J. Single Number
https://leetcode.com/problems/single-number/

本题最有价值的方法是实现了位运算。
详见本题解答部分解法4.
## Method Best: 位运算

```Java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0;
        for(int n : nums){
            a = a^n;
        }
        return a;
    }
}
```
