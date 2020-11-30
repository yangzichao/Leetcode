# 560 Subarray-Sum-Equals-K

difficulty: Medium

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given an array of integers and an integer <b>k</b>, you need to find the total number of continuous subarrays whose sum equals to <b>k</b>.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b>nums = [1,1,1], k = 2
<b>Output:</b> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li>The length of the array is in range [1, 20,000].</li>
	<li>The range of numbers in the array is [-1000, 1000] and the range of the integer <b>k</b> is [-1e7, 1e7].</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int subarraySum(int[] nums, int k) {
        /*
        以下面的为例啊
        index  0 1 2  3  4  5  6  7
        value  3 4 7  2  -3 1  4  2 
        preSum 3 7 14 16 13 14 18 20
        如果 preSum[i] - preSum[j] == 0, 说明 i j 之间的和是 0，
        如果 preSum[i] - preSum[j] == k, 说明 i j 之间的和是 k.
        因此，对于 preSum[i] - k == preSum[j], 如果能找到 preSum[j], 就可以符合。
        这就是 Two Sum! 这个题和 Two Sum 竟然一样。
        当然了，这个题不能用Set, 因为 Two Sum 没有重复的，只有唯一解，但是这个题，
        很可能出现重复的 preSum。
        例如，
        
        [0,0,0,0,0,0,0,0,0,0]
        0
        */
        
        if(nums.length < 1) return 0;
        Map<Integer, Integer> preSums = new HashMap<>();
        preSums.put(0, 1);
        int curSum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            int target = curSum - k;
            if(preSums.containsKey(target)){
                count += preSums.get(target);
            }
            preSums.put(curSum, preSums.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }
}
        */
​
```
