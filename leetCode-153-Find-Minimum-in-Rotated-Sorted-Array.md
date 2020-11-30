# 153 Find-Minimum-in-Rotated-Sorted-Array

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
<p>(i.e., &nbsp;<code>[0,1,2,4,5,6,7]</code>&nbsp;might become &nbsp;<code>[4,5,6,7,0,1,2]</code>).</p>
<p>Find the minimum element.</p>
<p>You may assume no duplicate exists in the array.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> [3,4,5,1,2] 
<strong>Output:</strong> 1
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> [4,5,6,7,0,1,2]
<strong>Output:</strong> 0
</pre>
</div></section>
 
 ## Method One 
 我们相当于一次检查 pivot - 1, pivot , pivot + 1 三个点。
 由于我们的模板，不可能让 pivot 处于 最后的位置，所以 pivot + 1 不能够超出范围。      
 而 pivot - 1
```java
class Solution {
  public int findMin(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    } // 这个是为 mid + 1 排雷，如果有两个元素， mid + 1 就不会超出范围。

    int left = 0, right = nums.length - 1;

    if (nums[right] > nums[0]) {
      return nums[0];
    } // 这个是防止本身就有序的，为后面 mid - 1 排雷。


    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > nums[mid + 1]) {
        return nums[mid + 1];
      }

      if (nums[mid - 1] > nums[mid]) {
        return nums[mid];
      }

      // if the mid elements value is greater than the 0th element this means
      // the least value is still somewhere to the right as we are still dealing with elements
      // greater than nums[0]
      if (nums[mid] > nums[0]) {
        left = mid + 1;
      } else {
        // if nums[0] is greater than the mid value then this means the smallest value is somewhere to
        // the left
        right = mid - 1;
      }
    }
    return 10000;

}
}

````


``` Java
class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        
        if(nums[left] < nums[right]) return nums[left];
        
        while( left <= right ) {
            int pivot = left + ( right - left ) / 2;
            
            if( nums[pivot] > nums[pivot + 1]) {
                return nums[pivot + 1];
            }
            if( nums[pivot] < nums[pivot - 1]) {
                return nums[pivot];
            }
            
            if( nums[left] < nums[pivot] ) {
                left = pivot + 1;
            }
            
            if( nums[right] > nums[pivot]) {
                right = pivot;
            }
        }
        
        return 10000;
    }
}
​
````
