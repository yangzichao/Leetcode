# 311 Sparse-Matrix-Multiplication

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
<div><p>Given two <a href="https://en.wikipedia.org/wiki/Sparse_matrix" target="_blank">sparse matrices</a> <b>A</b> and <b>B</b>, return the result of <b>AB</b>.</p>
<p>You may assume that <b>A</b>'s column number is equal to <b>B</b>'s row number.</p>
<p><b>Example:</b></p>
<pre><b>Input:
</b><strong>A</strong> = [
  [ 1, 0, 0],
  [-1, 0, 3]
]
<strong>B</strong> = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]
<strong>Output:</strong>
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
<b>AB</b> = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>1 &lt;= A.length, B.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length, B[i].length &lt;= 100</code></li>
	<li><code>-100 &lt;= A[i][j], B[i][j]&nbsp;&lt;= 100</code></li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        // 不停剪枝就好了。其实就是普通乘法多了两个判断, 有0那我整个就不算了。
        int mA = A.length;
        int nA = A[0].length;
        int mA = B.length; // if nA != mA 要报错。
        int nB = B[0].length;
        
        int[][] ans = new int[mA][nB];
        
        for(int rowA = 0; rowA < mA; rowA++ ) {
            // index 其实就是 colA, 也等于 rowB.
            for(int index = 0; index < nA; index++ ) {
                if( A[rowA][index] == 0) {
                    continue;
                }
                for(int colB = 0; colB < nB; colB++ ) {
                    if(B[index][colB] == 0) {
                        continue;
                    }
                    int val = A[rowA][index] * B[index][colB];
                    ans[rowA][colB] += val;
                }
            }
        }
        return ans;
    }
}
​
```
