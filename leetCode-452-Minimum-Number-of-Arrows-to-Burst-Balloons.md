# 452 Minimum-Number-of-Arrows-to-Burst-Balloons 
 
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
<div><p>There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 10<sup>4</sup> balloons.</p>
<p>An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with x<sub>start</sub> and x<sub>end</sub> bursts by an arrow shot at x if x<sub>start</sub> ≤ x ≤ x<sub>end</sub>. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.</p>
<p><b>Example:</b></p>
<pre><b>Input:</b>
[[10,16], [2,8], [1,6], [7,12]]
<b>Output:</b>
2
<b>Explanation:</b>
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
</pre>
<p>&nbsp;</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int findMinArrowShots(int[][] points) {
        // 这个题很有意思，和 merge interval 是反着来的。
        // 扎气球，如果有重叠的就一箭多雕
        // 之前我们 merge interval  都扩大这个区间，
        // 而这个题我们反其道而行之，缩小区间。因为
        // 1 - 10, 2-4, 5-8， 都是一个大区间 1- 10，但是不可能一箭全灭。
        // 我们遇到 1 - 10， 2-4 就直接取为 1 - 4 就好了。这样 1-4 就和 5-8不重复。
​
        if( points.length < 2) {
            return points.length;
        }
        Arrays.sort(points, (a,b)-> a[0] - b[0]);
        int count = 1;
​
        int curEnd = points[0][1];
        for(int[] point : points) {
            if( point[0] <= curEnd ){
                curEnd = Math.min(curEnd, point[1]);
                continue;
            }
            curEnd = point[1];
            count++;
        } 
        return count;
    }
}
​
```