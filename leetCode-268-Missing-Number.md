# 268J. Missing Number
https://leetcode.com/problems/missing-number/

## Method Good:
这个题可以转化为 041 
041 是找消失的最大正整数。
那么这个题一样，用同样的代码，变成寻找消失的最大正整数，
如果找不到，那么说明消失的是0.

```java
class Solution {
    public int missingNumber(int[] nums) {
        int N = nums.length;
        for(int i = 0; i < N; i++){
            if(nums[i] <= 0 || nums[i] > N) {
                nums[i] = N + 1;
            }
        }
        for(int i = 0; i < N; i++){
            int num = Math.abs(nums[i]); 
            if(num > N ) continue;
            num --;
            if( nums[num] > 0){
                nums[num] *= -1;
            }    
        }
        
        for(int i = 0; i < N; i++){
            if(nums[i] >= 0){
                return i + 1;
            }
        }
        return 0;
    }
}
```

## Method Best 之 位运算
一共有应有 n + 1 个数，和 n 个 index, 
利用 a^a = 0。如果一个数的 从 N + 1开始，对
数组所有的index 和 值 都连续位运算，那么单独剩下来的数
就是missing的数。

```java
class Solution {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for(int i = 0; i < nums.length; i++){
            missing^=nums[i];
            missing^=i;
        }
        return missing;
    }
}
```

## Method Best 之 改写方法1
https://leetcode.com/problems/missing-number/discuss/69961/Share-four-different-solutions