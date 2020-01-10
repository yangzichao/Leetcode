# 448J. Find All Numbers Disappeared in an Array
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

这个题可以转化为041解答

## 使用041

因为041是找到第一个消失的正整数。
这个题找消失的数，那么就直接把所有消失的存起来就好了。
```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        int N = nums.length;
        
        // 这道题不需要这个过程。
        // for(int i = 0; i < N; i++){
        //     if(nums[i] <= 0 || nums[i] > N) {
        //         nums[i] = N + 1;
        //     }
        // }
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
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
```
