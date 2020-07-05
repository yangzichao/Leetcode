# 509 Fibonacci-Number 
 
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
<div><p>The&nbsp;<b>Fibonacci numbers</b>, commonly denoted&nbsp;<code>F(n)</code>&nbsp;form a sequence, called the&nbsp;<b>Fibonacci sequence</b>, such that each number is the sum of the two preceding ones, starting from <code>0</code> and <code>1</code>. That is,</p>
<pre>F(0) = 0,&nbsp; &nbsp;F(1)&nbsp;= 1
F(N) = F(N - 1) + F(N - 2), for N &gt; 1.
</pre>
<p>Given <code>N</code>, calculate <code>F(N)</code>.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> F(2) = F(1) + F(0) = 1 + 0 = 1.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> F(3) = F(2) + F(1) = 1 + 1 = 2.
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> F(4) = F(3) + F(2) = 2 + 1 = 3.
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<p>0 ≤ <code>N</code> ≤ 30.</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int fib(int N) {
        //事实上我们只需要三个储存空间就行；
        if( N == 0 ) {
            return 0;
        }
        int prev2 = 0;
        int prev = 1;
        int ans = 1;
        for(int i = 2; i < N + 1; i++ ) {
            ans = prev + prev2;
            prev2 = prev;
            prev = ans;
        }
        return ans;
    }
​
//  以下比较原始，因为空间浪费多；
//     public int fib(int N) {
//         if( N == 0 ) {
//             return 0;
//         }
        
//         int[] ans = new int[N + 1];
//         ans[0] = 0;
//         ans[1] = 1;
//         for(int i = 2; i < N + 1; i++) {
//             ans[i] = ans[i - 1] + ans[i - 2];
//         }
//         return ans[N];
//     }
}
​
```