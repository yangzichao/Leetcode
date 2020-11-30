# 704 Binary-Search

difficulty: Easy

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given a <strong>sorted</strong> (in ascending order) integer array <code>nums</code> of <code>n</code> elements and a <code>target</code> value, write a function to search <code>target</code> in <code>nums</code>. If <code>target</code> exists, then return its index, otherwise return <code>-1</code>.</p>
<p><br>
<strong>Example 1:</strong></p>
<pre><strong>Input:</strong> <code>nums</code> = [-1,0,3,5,9,12], <code>target</code> = 9
<strong>Output:</strong> 4
<strong>Explanation:</strong> 9 exists in <code>nums</code> and its index is 4
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> <code>nums</code> = [-1,0,3,5,9,12], <code>target</code> = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> 2 does not exist in <code>nums</code> so return -1
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ol>
	<li>You may assume that all elements in <code>nums</code> are unique.</li>
	<li><code>n</code> will be in the range <code>[1, 10000]</code>.</li>
	<li>The value of each element in <code>nums</code> will be in the range <code>[-9999, 9999]</code>.</li>
</ol>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int pivot = left + (right - left ) / 2;
            if(target < nums[pivot]) {
                right = pivot - 1;
            }else if( target > nums[pivot]) {
                left = pivot + 1;
            }else {
                return pivot;
            }
        }
        return -1;
    }
}
​
```
