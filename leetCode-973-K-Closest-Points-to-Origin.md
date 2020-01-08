# 973J. K Closest Points to Origin
https://leetcode.com/problems/k-closest-points-to-origin/

这个题据称是Amazon的OA题。
这个题和215完全一样。
主要的价值在于学习如何写int[] 的 Comparator.
写法一
```java
    PriorityQueue<int[]> heap = new PriorityQueue<int[]>(K + 1, new pseudoComparator());
    private class pseudoComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return - a[0] + b[0];
        }
    }
写法二
```java

    PriorityQueue<int[]> q = new PriorityQueue<int[]>(k + 1, new Comparator<int[]>(){
        // @override
        public int compare(int[] k, int[] l){
            return l[0] * l[0] + l[1] * l[1] - k[0] * k[0] - k[1] * k[1];
        }
    });
```

## Method PQ

```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] distance = new int[points.length][2];
        int id = 0;
        for(int[] coordinates : points){
            distance[id][0] = coordinates[0]*coordinates[0] + coordinates[1]*coordinates[1];
            distance[id][1] = id;
            id++;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(K + 1, new pseudoComparator());
        for(int[] enqueueItem: distance){
            heap.offer(enqueueItem);
            if(heap.size() > K){
                heap.poll();
            }
        }
        
        int[][] ans = new int[K][2];
        while(K > 0){
            ans[--K] = points[heap.poll()[1]];
        }
        return ans;
    }
    
    private class pseudoComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return - a[0] + b[0];
        }
    }
}
```


## Method 2 
自然不能忘记用 Quick Select ， 也见215