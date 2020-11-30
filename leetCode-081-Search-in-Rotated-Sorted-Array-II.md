# 081 Search-in-Rotated-Sorted-Array-II 
 
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
<div><p>Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.</p>
<p>(i.e., <code>[0,0,1,2,2,5,6]</code> might become <code>[2,5,6,0,0,1,2]</code>).</p>
<p>You are given a target value to search. If found in the array return <code>true</code>, otherwise return <code>false</code>.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [2<code>,5,6,0,0,1,2]</code>, target = 0
<strong>Output:</strong> true
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2<code>,5,6,0,0,1,2]</code>, target = 3
<strong>Output:</strong> false</pre>
<p><strong>Follow up:</strong></p>
<ul>
	<li>This is a follow up problem to&nbsp;<a href="/problems/search-in-rotated-sorted-array/description/">Search in Rotated Sorted Array</a>, where <code>nums</code> may contain duplicates.</li>
	<li>Would this affect the run-time complexity? How and why?</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
public class Solution {
​
    public boolean search(int[] nums, int target) {
        /**
        因为有 duplicates, 所以 worst case O(N);
        */
        int left = 0;
        int right = nums.length - 1;
        while( left <= right ) {
            int pivot = left + (right - left)/2;
            if (nums[ pivot ] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[pivot] < nums[right] || nums[pivot] < nums[left]) {
                if (target > nums[ pivot ] && target <= nums[ right ]) {
                    left = pivot + 1;
                } else {
                    right = pivot - 1;
                }
            //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[ pivot ] > nums[ left ] || nums[ pivot ] > nums[right]) {
                if (target < nums[ pivot ] && target >= nums[ left ]) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            //If we get here, that means nums[ left ] == nums[ pivot ] == nums[ right ], then shifting out
            //any of the two sides won't change the result but can help remove duplicate from
            //consideration, here we just use end-- but left++ works too
            } else {
                right--;
            }
        }
        
        return false;
    }
}
​
​
  
​
```