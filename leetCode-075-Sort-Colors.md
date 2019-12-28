# 075J. Sort Colors
https://leetcode.com/problems/sort-colors/

这个题是三切分快排的核心算法。非常重要。
## Method Best: 
不明白的话这个题可以参考QuickSort3Way。
以下就是三切分快排的核心算法。由于只有三个不重复的元素，所以不需要切分子数组。
唯一可以提升的是，可以用位操作来swap.

a = a^b;
b = a^b;
a = a^b;
```java
class Solution {
    public void sortColors(int[] nums) {
        //这个题相关的有 三向快排 remove duplicates.
        int N = nums.length;
        int loT = 0; 
        int hiT = N - 1;
        int i = 0;
        int v = 1;
        while( i <= hiT){
            if(nums[i] < v){
                int temp = nums[i];
                nums[i]   = nums[loT];
                nums[loT] = temp;
                i++;
                loT++;
            }else if(nums[i] > v){
                int temp = nums[i];
                nums[i] = nums[hiT];
                nums[hiT] = temp;
                hiT--;
            }else{
                i++;
            }
        }
        return;
    }
}
```