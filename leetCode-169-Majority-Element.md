# 169J. Majority Element

https://leetcode.com/problems/majority-element/

## Method 1 HashMap TS O(N) O(N)

这个办法是 用值本身对应出现的次数
并且可以一边加一边判断是否要 return nums[i]。所以最后的 return 0 已经不重要了。

```java
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

<pre>
可以这么想，如果令major element = + 1, 其他的为 -1.
因为一定存在major element, 数组之和必为正。
从头开始iterate，先取第一个为candidate, 和它相同就count+1,
反之-1。当count == 0，下一次循环的时候，取该element为candidate。
可以想像，由于主成分更多，即使中间无数次选到了错的candidate,最后一定会被
主成分“逆转”，最后还是变成以主成分为“正”的情形。
</pre>

```Java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0; // 新建 count
        int candidate = 0; // 新建candidate,是什么不重要
        for(int n : nums){
            if( count == 0){
                // 对于第一个元素，或者发生了逆转，会赋值candidate 为 n，
                //发生逆转意味着，前面的子数组，有一样多数量的 主成分和非主成分，剩下的子数组一定有更多的主成分
                candidate = n;
            }
            count += candidate == n ? 1 : -1;
        }
        return candidate; //最后剩下来的candidate 一定是正确的candidate
    }
}
```
