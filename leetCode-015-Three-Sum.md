# 015J. Three Sum
https://leetcode.com/problems/3sum/


## Method: O(N^2)

```Java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        int target = 0;

        for(int i = 0; i < nums.length - 2; i++){

            target = 0 - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            // 这个是为了避免重复的 i
            if(i == 0 || (i> 0 && nums[i]!=nums[i-1] )){
                while(left < right){
                    if( nums[left] + nums[right] < target){
                        left++;
                    }else if(nums[left] + nums[right] > target){
                        right--;
                    }else{
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        ans.add(temp);
                        //以下两句是为了避免重复的 left 和 right
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}
```
