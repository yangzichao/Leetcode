# 435 Non-overlapping-Intervals

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
<div><p>Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.</p>
<ol>
</ol>
<p>&nbsp;</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> [[1,2],[2,3],[3,4],[1,3]]
<b>Output:</b> 1
<b>Explanation:</b> [1,3] can be removed and the rest of intervals are non-overlapping.
</pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> [[1,2],[1,2],[1,2]]
<b>Output:</b> 2
<b>Explanation:</b> You need to remove two [1,2] to make the rest of intervals non-overlapping.
</pre>
<p><b>Example 3:</b></p>
<pre><b>Input:</b> [[1,2],[2,3]]
<b>Output:</b> 0
<b>Explanation:</b> You don't need to remove any of the intervals since they're already non-overlapping.
</pre>
<p>&nbsp;</p>
<p><b>Note:</b></p>
<ol>
	<li>You may assume the interval's end point is always bigger than its start point.</li>
	<li>Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.</li>
</ol>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2) {
            return 0;
        }
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int count = -1;
        int curEnd = intervals[0][1];
        for(int[] interval : intervals) {
            if(interval[0] < curEnd) {
                count++;
                curEnd = Math.min( interval[1], curEnd );
            }else{
                curEnd = interval[1];
            }
        }
        return count;
    }
}
​
```
