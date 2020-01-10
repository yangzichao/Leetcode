# 295J.  Find Median from Data Stream
https://leetcode.com/problems/find-median-from-data-stream/

## Method good: Two PQ
log(k)

```java
class MedianFinder {
    private PriorityQueue<Integer> maxPQ;
    private PriorityQueue<Integer> minPQ;
    /** initialize your data structure here. */
    public MedianFinder() {
        maxPQ = new PriorityQueue<>((a,b) -> b - a );
        minPQ = new PriorityQueue<>((a,b) -> a - b);
    }
    
    public void addNum(int num) {
        maxPQ.offer(num);
        minPQ.offer(maxPQ.poll());
        while(minPQ.size() > maxPQ.size()){
            maxPQ.offer(minPQ.poll());
        }
    }
    
    public double findMedian() {
        return minPQ.size() == maxPQ.size() ? (minPQ.peek() + maxPQ.peek())/2.0 : maxPQ.peek();
    }
}
```
