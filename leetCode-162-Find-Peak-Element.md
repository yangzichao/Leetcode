# 162J. Find Peak Element

https://leetcode.com/problems/find-peak-element/

## Method 普通
这是完全普通按照定义来的，
由于只需要返回任意一个峰值，所以很简单。
```Java
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < nums.length - 1; i++){
            if(nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                return i;
            }
        }
        return nums[0] > nums[1] ? 0:nums.length - 1;
    }
}
```
## Method Best: 二分法查找

下面这个为什么不行？
因为取 pivot - 1, pivot + 1。edge case会太多。
```Java
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int pivot = 0;
        while(left <= right){
            pivot = left + (right - left)/ 2;
            if( nums[pivot] > nums[pivot - 1] && nums[pivot] > nums[pivot + 1]){
                return pivot;
            }else if( nums[pivot] > nums[pivot - 1] && nums[pivot] < nums[pivot + 1] ){
                left = pivot + 1;
            }else{
                right = pivot - 1;
            }
        }

        return left;
    }
}
```

可行的代码如下

```Java
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int pivot = 0;
        while(left < right){
            pivot = left + (right - left)/ 2;

            if(nums[pivot] > nums[pivot + 1]){
                 // 如果 pivot 比 pivot + 1 大，说明左侧有极大
                // 分割区域，包含 pivot
                right = pivot;
            }else{
                // 反之说明右侧有极大，
                // 分割区域，不包含 pivot.
                left = pivot + 1;
            }
        }
        //用 left < right作为判据 最终 left = right
        // 这里由于是找极大，和一般的找特定值不同。
        return right;
    }
}
```
同样的思想，也可以这么写

```Java
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int pivot = 0;
        while(left < right){
            pivot = left + (right - left)/ 2;

            if(nums[pivot] <  nums[pivot + 1]){
                left = pivot + 1;
            }else{
                right = pivot;
            }
        }

        return right;
    }
}
```
