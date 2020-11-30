# 057 Insert-Interval

difficulty: Hard

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given a set of <em>non-overlapping</em> intervals, insert a new interval into the intervals (merge if necessary).</p>
<p>You may assume that the intervals were initially sorted according to their start times.</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>Output:</strong> [[1,5],[6,9]]
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> intervals = <code>[[1,2],[3,5],[6,7],[8,10],[12,16]]</code>, newInterval = <code>[4,8]</code>
<strong>Output:</strong> [[1,2],[3,10],[12,16]]
<strong>Explanation:</strong> Because the new interval <code>[4,8]</code> overlaps with <code>[3,5],[6,7],[8,10]</code>.</pre>
<p><strong>NOTE:</strong>&nbsp;input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.</p>
</div></section>
 
 ## Method One 
 
 主要是把问题分成三部分，之前和之后的直接加上。
 中间的需要处理一下。
``` Java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length < 1) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        ArrayList<int[]> ans = new ArrayList<>();
​
        int i = 0;
        while( i < intervals.length && intervals[i][1] < newInterval[0] ) {
            ans.add(intervals[i]);
            i++;
        }
        while(i < intervals.length && intervals[i][0] <= newInterval[1] ) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        ans.add(newInterval);
​
        while( i < intervals.length ){
            ans.add(intervals[i]);
            i++;
        }
​
        int[][] res = new int[ans.size()][2];
        for(int j = 0; j < ans.size(); j++ ){
            res[j][0] = ans.get(j)[0];
            res[j][1] = ans.get(j)[1];
        }
        return res;
    }
}
​
```
