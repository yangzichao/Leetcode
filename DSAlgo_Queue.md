# Queue


## 1 Regular Queue



## 2 Priority Queue
优先队列的应用非常多。
比较经典的题有:
* 找一个数组第k大的元素: [LC215](leetCode-215-Kth-Largest-Element-in-an-Array.md)
* 安排任务 [LC621](leetCode-621-Task-Scheduler.md)
* 安排会议室 [LC253](leetCode-253-Meeting-Rooms-II.md)
* [295]
  
 和215相似 但非最佳办法[LC347]
### 2. 代码
```java
PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2); // min heap
PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1); // max heap
```

数组 heap();
```java
PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new pseudoComparator());
    private class pseudoComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return a[0] - b[0];
        }
    }
```

```java
    PriorityQueue<int[]> q = new PriorityQueue<int[]>(k + 1, new Comparator<int[]>(){
        @Override
        public int compare(int[] k, int[] l){
            return l[0] * l[0] + l[1] * l[1] - k[0] * k[0] - k[1] * k[1];
        }
    });
```

## 3 Monotonic Queue
> [参考1](https://medium.com/algorithms-and-leetcode/monotonic-queue-explained-with-leetcode-problems-7db7c530c1d6)
> [参考2](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/204290/Monotonic-Queue-Summary)
### 3.1 定义
* **Monotonic increasing queue** 
单调上升队列： 从后面添加新元素，如果前面有比自己大(或者不比自己小)的元素，让他们都出列之后再入列。
* **Monotonic decreasing queue**
单调下降队列： 从后面添加新元素，如果前面有比自己小(或者不比自己大)的元素，让他们都出列之后再入列。

解释1，在一个武力说话的世界里排队，我把前面所有比我弱鸡的人都打飞，直到打不过为止就老实排着。
解释2，类比游戏的定段制度。

### 3.2 实现
原则上只需要从队尾加入和删除就行。但是结合题目，一般不仅仅只考察它，用Deque比较灵活。
### 3.3相关题目
* [LC084]
* [LC085]
* [LC122]
* [LC239](leetCode-239-Sliding-Window-Maximum.md)
* [LC496]
* [LC503]
* [LC581]
* [LC739]
* [LC862](leetCode-862-Shortest-Subarray-with-Sum-at-Least-K.md)
* [LC901]
* [LC907]