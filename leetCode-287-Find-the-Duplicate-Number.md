# 287 Find-the-Duplicate-Number

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
<div><p>Given an array <i>nums</i> containing <i>n</i> + 1 integers where each integer is between 1 and <i>n</i> (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> <code>[1,3,4,2,2]</code>
<b>Output:</b> 2
</pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> [3,1,3,4,2]
<b>Output:</b> 3</pre>
<p><b>Note:</b></p>
<ol>
	<li>You <b>must not</b> modify the array (assume the array is read only).</li>
	<li>You must use only constant, <i>O</i>(1) extra space.</li>
	<li>Your runtime complexity should be less than <em>O</em>(<em>n</em><sup>2</sup>).</li>
	<li>There is only one duplicate number in the array, but it could be repeated more than once.</li>
</ol>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int findDuplicate(int[] nums) {
        // 这个特殊在 n + 1的长度里面的整数是从 1 - n 的，因此 index 就可以用来做标记。
        // 从 0 出发，就可以找到，这个和那个 LinkedList 一样
        int slow = nums[0];
        int fast= nums[nums[0]];
        
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
​
```
