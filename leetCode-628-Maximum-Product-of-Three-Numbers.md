# 628 Maximum-Product-of-Three-Numbers

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
<div><p>Given an integer array, find three numbers whose product is maximum and output the maximum product.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> [1,2,3]
<b>Output:</b> 6
</pre>
<p>&nbsp;</p>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> [1,2,3,4]
<b>Output:</b> 24
</pre>
<p>&nbsp;</p>
<p><b>Note:</b></p>
<ol>
	<li>The length of the given array will be in range [3,10<sup>4</sup>] and all elements are in the range [-1000, 1000].</li>
	<li>Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.</li>
</ol>
<p>&nbsp;</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        
        for(int n : nums) {
            if(n <= min1) {
                min2 = min1;
                min1 = n;
            }else if( n <= min2) {
                min2 = n;
            }
            
            if( n >= max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            }else if( n > max2 ) {
                max3 = max2;
                max2 = n;
            }else if( n > max3 ) {
                max3 = n;
            }
        }
        
        return Math.max(min1*min2*max1, max1*max2*max3);
    }
}
​
​
​
```
