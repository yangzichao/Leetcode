# 215J. Kth Largest Element in an Array

https://leetcode.com/problems/kth-largest-element-in-an-array/

是佛佛作业题

这个题当下最主要的是实现了对 priority queue的学习
这是最直接的应用
除此之外，方法2，是对2way quicksort的一个应用。
## Method PQ 

可以用 max heap
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Max Heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        for(int i = 0; i < nums.length; i++){
            heap.offer(nums[i]);
        }
        int count = 0;
        while(count < k - 1){
            heap.poll();
            count++;
        }
        return heap.poll();
    }
}
```
不如用 min heap
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min Heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        for(int i = 0; i < nums.length; i++){
            heap.offer(nums[i]);
            if(heap.size() > k){
                heap.poll();
            }
        }
        return heap.poll();
    }
}
```
## Method: Quick Select






















第一次直接写了一个two way quick sort
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        sort(nums,0, nums.length - 1);
        return nums[k - 1];
    }
    
    public void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    return;
  }
    public void sort(int[] nums, int lo, int hi){
        if(hi <= lo) return;
        int j = partition(nums, lo , hi);
        sort(nums,lo, j - 1);
        sort(nums, j+ 1, hi);
    }
    public int partition(int[] nums, int lo, int hi){
        int v = nums[lo];
        int f = lo; // front pointer starts from lower bound
        int b = hi + 1; //back pointer starts from upper bound
        while(true){
            while(nums[++f] > v ){
                if(f == hi){
                    break;
                }
            }
            while(nums[--b] < v){
                if(b == lo){
                    break;
                }
            }
            if(b <= f){
                break;
            }
            swap(nums, b, f);
        }
        swap(nums, lo, b);
        return b;
    }
}
```