# 219J. Contains Duplicate II
https://leetcode.com/problems/contains-duplicate-ii/
上一题是 217J
下一题是 220J

## 方法 最佳
<pre>
用一个Set当Sliding Window, 就是这个Set 只包含第 i - 1 和其后 k - 1个元素
然后看第 i 个 是不是在这个Set里面。如果在的话，就直接返回true, 反之就一直到末尾，
然后返回false.
</pre>
```Java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> mySet = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(mySet.contains(nums[i])){
                return true;
            }
            mySet.add(nums[i]);
            if(mySet.size()>k){
                mySet.remove(nums[i - k]);
            }
        }
        return false;
    }
}
```
