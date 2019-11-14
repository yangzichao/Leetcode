# 169J. Majority Element
https://leetcode.com/problems/majority-element/

## Method 1 HashMap TS O(N) O(N)
这个办法是 用值本身对应出现的次数
并且可以一边加一边判断是否要return nums[i]。所以最后的return 0 已经不重要了。
```Java
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> myMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(myMap.get(nums[i]) == null){
                myMap.put(nums[i],1);
            }else{
                int temp = myMap.get(nums[i])+1;
                myMap.put(nums[i],temp);
            }
            if(myMap.get(nums[i]) + 1 > (nums.length + 1)/2){
                return nums[i];
            }
        }
        return 0;
    }
}
```
## Method 2 Approach 6: Boyer-Moore Voting Algorithm TS O(N) O(1)
这个是好方法 学习一下
https://leetcode.com/problems/majority-element/solution/
