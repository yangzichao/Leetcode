# 229J. Majority Element II

https://leetcode.com/problems/majority-element-ii/

## Method Best : Boyer-Moore Voting Algorithm T:O(N), S:(1);
[参见](leetCode-169-Majority-Element.md)
这里是一个推广，如果有两个主成分的话，那么他们都必然可以被取到。

```Java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if(nums.length == 0 ){
            return new ArrayList<Integer>();
        }
        List<Integer> list = new ArrayList<>();
        int count1 = 0, count2 = 0;
        int pos1 = 0, pos2 = 0;
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(n == nums[pos1]){
                count1 += 1;
            }else if(n == nums[pos2]){
                count2 += 1;
            }else if(count1 == 0){
                pos1 = i;
                count1 = 1;
            }else if(count2 == 0){
                pos2 = i;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }
        }
        //由于不保证一定出现两个majority element,只保证一个
        //要检查一下是不是两个都是
        count1 = 0;
        count2 = 0;

        for(int n : nums){
            if(n == nums[pos1]){
                count1++;
            }
            else if( n == nums[pos2]){
                // else if 很重要，如果pos1和pos2都取到了同一个值，
                // 这里他们取到同一个值只能是因为数组中只有一个重复的数。如[1,1,1]
                // 那么我们用这个else if就避免了给他们中的第二个计数。
                count2++;
            }
        }
        if(count1 > nums.length/3){
            list.add(nums[pos1]);
        }
        if(count2 > nums.length/3){
            list.add(nums[pos2]);
        }
        return list;
    }
}
```
