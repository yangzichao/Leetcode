# \*253J. Meeting Rooms II

https://leetcode.com/problems/meeting-rooms-ii/

## Method Trivial

看似 Trivial, 实则并非如此。

1. 首先我们需要对输入进行一个排序，按开始的时间捋顺了。这会花费 n log n
2. 建立一个按结束时间排序的最小堆。这个相当于我们的线程数量。
3. 这个最小堆，顶上的就是目前完成时间最早的线程(thread).
4. 那么新的任务如果开始的时间比堆顶上的完成时间还早，说明我们需要新开一个线程。
5. 反之，说明当前的线程可以依次处理这两个任务，我们把堆顶的任务结束时间更新。
6. 更新完之后，堆顶可能会下沉。结束时间更早的线程(thread) 会上浮上来。
7. 因此我们知道，heap 的 size 就是最多的线程数量。

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length < 1) return 0;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> minheap = new PriorityQueue<>((a,b) -> a[1] - b[1] );
        minheap.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            int[] interval = intervals[i];
            int[] curThread = minheap.poll();
            if(curThread[1] <= interval[0]){
                curThread[1] = interval[1];
            }else{
                minheap.offer(interval);
            }
            minheap.offer(curThread);
        }
        return minheap.size();
    }
}
```
