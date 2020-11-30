# 276 Paint-Fence 
 
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
<div><p>There is a fence with n posts, each post can be painted with one of the k colors.</p>
<p>You have to paint all the posts such that no more than two adjacent fence posts have the same color.</p>
<p>Return the total number of ways you can paint the fence.</p>
<p><b>Note:</b><br>
n and k are non-negative integers.</p>
<p><b>Example:</b></p>
<pre><b>Input:</b> n = 3, k = 2
<b>Output:</b> 6
<strong>Explanation: </strong>Take c1 as color 1, c2 as color 2. All possible ways are:
&nbsp;           post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
&nbsp;  2         c1     c2     c1 
&nbsp;  3         c1     c2     c2 
&nbsp;  4         c2     c1     c1&nbsp; 
   5         c2     c1     c2
&nbsp;  6         c2     c2     c1
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int numWays(int n, int k) {
        if(n == 0){
            return 0;
        }
​
        if( n == 1){
            return k;
        }
        int prevPrev = k;
        int prev = k * k;
        for(int i = 3; i <= n; i++ ){
            int cur = (prevPrev + prev ) * (k - 1);
            prevPrev = prev;
            prev = cur;
        }
        return prev;
    }
}
​
/**
State Transition Equation: 状态转移方程
​
total[i] = same[i] + diff[i];
diff[i] =  total[i - 1] * (k - 1);
same[i] =  diff[i - 1]*1;  diff[i - 1] 就是 i - 1 和 i - 2 不同的所有方法
=> total[i] = total[i - 1] *(k - 1) + diff[i - 1];
=> total[i] = ( total[i - 1]  + total[i - 2] ) *(k - 1) ;
​
Boundary Conditions:
total[0] : 0; 这个无法给出正确的total[2], 所以这个题必须给出 1，2 的情况作为边界条件
total[1] : k;
total[2] : k*k;
*/
​
```