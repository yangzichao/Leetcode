# 528 Random-Pick-with-Weight

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
<div><p>Given an array <code>w</code> of positive integers, where <code>w[i]</code> describes the weight of index <code>i</code>(0-indexed), write a function <code>pickIndex</code>&nbsp;which <strong>randomly</strong>&nbsp;picks an index&nbsp;in proportion&nbsp;to its weight.</p>
<p><em>For example, given an input list of values w = [2, 8], when we pick up a number out of it, the chance is that 8&nbsp;times out of 10 we should pick the number 1&nbsp;as the answer since it's the second element of the array (w[1] = 8).</em></p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input</strong>
["Solution","pickIndex"]
[[[1]],[]]
<strong>Output</strong>
[null,0]
<strong>Explanation</strong>
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input</strong>
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
<strong>Output</strong>
[null,1,1,1,1,0]
<strong>Explanation</strong>
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.
Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>1 &lt;= w.length &lt;= 10000</code></li>
	<li><code>1 &lt;= w[i] &lt;= 10^5</code></li>
	<li><code>pickIndex</code>&nbsp;will be called at most <code>10000</code> times.</li>
</ul>
</div></section>
 
 ## Method One 
分析一下如何按权重随机。
假设我们有    
index 0 1 2 3 4       
value 1 2 3 4 5     
那么我们建一个长度为15的线段，那么就知道如果能均匀的落在这个线段上，
那么我们就按权重落在 index 上了。    
比如     
index  0    1   2   3     4   
range 0-1  1-3 3-6 6-10 10-15   
注意区间的开闭需要具体问面试官，其实无所谓。    
如果我们产生一个 0到1之间的随机数，乘以总长度，那就是随机的按权重选了。    
即 target = Math.random() * totalSum.    
如果 target = 4.5， 用我最爱的普通二分查找直到停止的模板，我们知道     
left = 2, right = 1. 这里我们显然应该返回 left.

```Java
class Solution {
    private int[] prefixSum;
    private int totalSum;
    
    public Solution(int[] w) {
        this.prefixSum = new int[w.length];
        
        int curSum = 0;
        for(int i = 0; i < w.length; i++) {
            curSum += w[i];
            prefixSum[i] = curSum;
        }
        
        this.totalSum = curSum;
    }
    
    public int pickIndex() {
        double target = this.totalSum * Math.random();
        int left = 0;
        int right = prefixSum.length - 1;
        while( left <= right ) {
            int pivot = left + (right - left ) / 2;
            int curVal = prefixSum[pivot];
            if(curVal < target ) {
                left = pivot + 1;
            }else {
                right = pivot - 1;
            }
        }
        return left;
    }
}
​
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
​
```
