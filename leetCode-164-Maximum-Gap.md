# 164 Maximum-Gap 
 
difficulty: Hard 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given an unsorted array, find the maximum difference between the successive elements in its sorted form.</p>
<p>Return 0 if the array contains less than 2 elements.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> [3,6,9,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The sorted form of the array is [1,3,6,9], either
&nbsp;            (3,6) or (6,9) has the maximum difference 3.</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> [10]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The array contains less than 2 elements, therefore return 0.</pre>
<p><b>Note:</b></p>
<ul>
	<li>You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.</li>
	<li>Try to solve it in linear time/space.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int maximumGap(int[] nums) {
        if(nums.length < 2){
            return 0;
        }
        // 这个题还不错。首先我们先扫一遍数组获得最大值 max 最小值 min
        // 那么我们知道 Ceiling (max - min) / (n - 1) 是最小的 Gap. 那么我们就这么办。
        
        int min = nums[0];
        int max = nums[0];
​
        for(int n : nums){
            min = Math.min(n, min);
            max = Math.max(n, max);
        }
        if(min == max){
            return 0;
        }
        
        int interval = (max - min)/(nums.length - 1) +  ((max - min)%(nums.length - 1) == 0 ? 0 : 1);
        // 虽然我们需要的是 n - 1 个桶，但是每个桶我们只需要记录它的最大和最小值就行了。
        // 我们不必像传统的桶排序一样，把所有的元素都记录下来。
        int[] mins = new int[nums.length - 1];
        int[] maxs = new int[nums.length - 1];
        //第 1 个桶 区间是 min, min + gap;
        //第 i 个桶 区间是 min + (i - 1)*gap + 1, min + i*gap;
        for(int i = 0; i < nums.length - 1; i++){
            mins[i] = Integer.MAX_VALUE;
            maxs[i] = Integer.MIN_VALUE;
        }
        for(int n : nums) {
            if(n == min){
                continue;
            }
            int index = (n - min)/interval;
            mins[index] = Math.min(mins[index], n);
            maxs[index] = Math.max(maxs[index], n);
        }
        int maxGap = Integer.MIN_VALUE;
        int last = 0;
        for(int i = 0; i < nums.length - 1; i++ ) {
            if( mins[i] == Integer.MAX_VALUE || maxs[i] == Integer.MIN_VALUE){
                continue;
            }
            maxGap = Math.max(maxGap, maxs[i] - mins[i]);
            maxGap = Math.max(maxGap, mins[i] - last);
            last = maxs[i];
        }
        return maxGap;
    }
}
​
```