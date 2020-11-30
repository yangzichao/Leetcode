# 362 Design-Hit-Counter

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
<div><p>Design a hit counter which counts the number of hits received in the past 5 minutes.</p>
<p>Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.</p>
<p>It is possible that several hits arrive roughly at the same time.</p>
<p><b>Example:</b></p>
<pre>HitCounter counter = new HitCounter();
// hit at timestamp 1.
counter.hit(1);
// hit at timestamp 2.
counter.hit(2);
// hit at timestamp 3.
counter.hit(3);
// get hits at timestamp 4, should return 3.
counter.getHits(4);
// hit at timestamp 300.
counter.hit(300);
// get hits at timestamp 300, should return 4.
counter.getHits(300);
// get hits at timestamp 301, should return 3.
counter.getHits(301); 
</pre>
<p><b>Follow up:</b><br>
What if the number of hits per second could be very large? Does your design scale?</p></div></section>
 
 ## Method One 
 
``` Java
class HitCounter {
    // 这个题理解题意比较重要。因为时间流是单向的，所以后面输入的时间不会更早.
    // 可以想像，我们需要保持这样一个时间的顺序，那么不能用无序的HashMap来储存时间。
    // 所以时间只能用 数组或者 LinkedList 来保存。
    // 我们的整个数据结构就像一个 sliding window 一样。
    // 用 int[] 来保存每个timestamp 和它相应的 hit 数量。
    // extract 了一个 private method 来做滑动窗口。
    private LinkedList<int[]> timeFlow;
    private int hits;
    private final int PAST = 300;
    /** Initialize your data structure here. */
    public HitCounter() {
        this.timeFlow = new LinkedList<int[]>();
        this.hits = 0;
    }
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        removeOutdated(timestamp);
        if(!timeFlow.isEmpty() && timeFlow.peekLast()[0] == timestamp) {
           timeFlow.getLast()[1] += 1;
           hits += 1;
        }else {
            timeFlow.offer(new int[]{timestamp, 1});
            hits += 1;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        removeOutdated(timestamp);
        return hits;      
    }
    
    private void removeOutdated(int timestamp) {
        while(!timeFlow.isEmpty() && timestamp - timeFlow.peekFirst()[0] >= PAST ) {
            hits -= timeFlow.getFirst()[1];
            timeFlow.removeFirst();
        }
    }
}
​
/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
​
​
​
```
