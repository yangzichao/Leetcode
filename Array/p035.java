class Solution {
    public int searchInsert(int[] nums, int target) {
        int counter = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            }
            if(nums[i] < target){
                counter += 1;
            }
        }

        return counter;
    }
}

/**
p035的通俗解法没什么意思。这个解法是双100%：
这题思路很容易，因为是排序好的数组；
从头到位循环，如果找到相等的，就直接返回index；
如果小于目标，计数器就加1。找不到相等的，就直接返回计数器。
*/
