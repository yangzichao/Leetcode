# 018J. 4Sum

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k){
            // start == nums.length 是边界条件
            // nums[start] * k > target || target > nums[nums.length - 1] * k 是early return, 可以加速
            // 从这两个条件轻易就能看出不存在合理的解。因此可以直接return.
            return res;
        }
        if (k == 2){
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i){
            // 跳过重复的数字
            if( i > start && nums[i] == nums[i - 1] ){
                continue;
            }
            for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1) ) {
                res.add(new ArrayList<>(Arrays.asList(nums[i])));
                res.get(res.size() - 1).addAll(set);
            }
        }
        return res;
    }
    
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1]))
                ++lo;
            else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
                --hi;
            else
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
        return res;
    }
}
```