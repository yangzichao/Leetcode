# 845 Longest-Mountain-in-Array 
 
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
<div><p>Let's call any (contiguous) subarray B (of A)&nbsp;a <em>mountain</em> if the following properties hold:</p>
<ul>
	<li><code>B.length &gt;= 3</code></li>
	<li>There exists some <code>0 &lt; i&nbsp;&lt; B.length - 1</code> such that <code>B[0] &lt; B[1] &lt; ... B[i-1] &lt; B[i] &gt; B[i+1] &gt; ... &gt; B[B.length - 1]</code></li>
</ul>
<p>(Note that B could be any subarray of A, including the entire array A.)</p>
<p>Given an array <code>A</code>&nbsp;of integers,&nbsp;return the length of the longest&nbsp;<em>mountain</em>.&nbsp;</p>
<p>Return <code>0</code> if there is no mountain.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input: </strong>[2,1,4,7,3,2,5]
<strong>Output: </strong>5
<strong>Explanation: </strong>The largest mountain is [1,4,7,3,2] which has length 5.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input: </strong>[2,2,2]
<strong>Output: </strong>0
<strong>Explanation: </strong>There is no mountain.
</pre>
<p><strong>Note:</strong></p>
<ol>
	<li><code>0 &lt;= A.length &lt;= 10000</code></li>
	<li><code>0 &lt;= A[i] &lt;= 10000</code></li>
</ol>
<p><strong>Follow up:</strong></p>
<ul>
	<li>Can you solve it using only one pass?</li>
	<li>Can you solve it in <code>O(1)</code> space?</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int longestMountain(int[] A) {
        /*
        方法一，从左往右扫记录每个位置的连续上升长度，从右往左扫记录每个位置的连续下降长度
        在别的位置，要么只有上升，要么只有下降，要么都没有。
        唯有在山顶的位置，既有上升，又有下降，用这种办法就可以找到山顶两侧的长度。
        那么加上山顶所占的位置，山的长度就是 up[i] + down[i] + 1;
        */
        int[] up = new int[A.length];
        int[] down = new int[A.length];
        for(int i = A.length - 2; i >= 0; i--){
            if( A[i] > A[i + 1] ){
                down[i] = down[i+1] + 1;
            }
        }
        int max = 0;
        for(int i = 0; i < A.length; i++){
            if( i > 0 && A[i] > A[i - 1] ){
                up[i] = up[i - 1] + 1;
            }
            if( down[i] > 0 && up[i] > 0){
                max = Math.max( down[i] + up[i] + 1, max);
            }
        }
        return max;
    }
}
​
```

```java
class Solution {
    public int longestMountain(int[] A) {
        /*
        one pass and O(1)
        的精髓在于，在一个山结束的时候 更新山的长度
        我们维护一个 up 和 down 来记录上坡和下坡的长度
        当遇到 平地 和开始新的山的时候 归零两个指针。
        */
        int up = 0;
        int down = 0;
        int max = 0;
        for(int i = 1; i < A.length; i++ ){
            if( A[i] == A[i - 1] || ( A[i] > A[i - 1]  && down > 0 )  ){
                up = 0;
                down = 0;
            }
            if( A[i] > A[i - 1]){
                up++;
            }
            if( A[i] < A[i - 1] ){
                down++;
            }
            if( up > 0 && down > 0){
                max  = Math.max( max, up + down + 1);
            }
        }
        return max;
    }
}
```