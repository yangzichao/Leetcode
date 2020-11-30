# 215J. Kth Largest Element in an Array

https://leetcode.com/problems/kth-largest-element-in-an-array/

是佛佛作业题
215 和 973 一模一样
这个题当下最主要的是实现了对 priority queue 的学习
这是最直接的应用
除此之外，方法 2，是对 2way quicksort 的一个应用。

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

第一次直接写了一个 two way quick sort

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

## Quick Select

```java
// import java.util.Random;
class Solution {
  int [] nums;

  public void swap(int a, int b) {
    int tmp = this.nums[a];
    this.nums[a] = this.nums[b];
    this.nums[b] = tmp;
  }


  public int partition(int left, int right, int pivot_index) {
    int pivot = this.nums[pivot_index];
    // 1. move pivot to end
    swap(pivot_index, right);
    int store_index = left;

    // 2. move all smaller elements to the left
    for (int i = left; i <= right; i++) {
      if (this.nums[i] < pivot) {
        swap(store_index, i);
        store_index++;
      }
    }

    // 3. move pivot to its final place
    swap(store_index, right);

    return store_index;
  }

  public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */

    if (left == right) // If the list contains only one element,
      return this.nums[left];  // return that element

    // select a random pivot_index
    Random random_num = new Random();
    int pivot_index = left + random_num.nextInt(right - left);
    pivot_index = partition(left, right, pivot_index);

    // the pivot is on (N - k)th smallest position
    if (k_smallest == pivot_index)
      return this.nums[k_smallest];
    // go left side
    else if (k_smallest < pivot_index)
      return quickselect(left, pivot_index - 1, k_smallest);
    // go right side
    return quickselect(pivot_index + 1, right, k_smallest);
  }

  public int findKthLargest(int[] nums, int k) {
    this.nums = nums;
    int size = nums.length;
    // kth largest is (N - k)th smallest
    return quickselect(0, size - 1, size - k);
  }
}

// 大致知道啥意思了
// 就是说我们还是搞快排那一套，选一个分界点，然后把比它小的都扔到他左侧去。
// 我们想要第 k 大的，实际上是 第 N - k + 1 小的。不过 index 正好是 N - k。
// partition 干的事儿就是把分割数先扔到子数组末尾，然后我们把比它小的都扔到最左侧，
// 然后把它回到该回的位置。
// 一旦我们发现我们把一个比它小的数都扔到他左边之后，它的 index == N - k, 那我们就知道找到了。
// 如果 index > N - k, 找左侧子数组，
// 如果 index < N - k 找右侧子数组。
// Quick Select 是为 这个题而生的，它不需要完成全部的排序，有很大可能早早返回正确值。
// 最差的情况就是老老实实的排序O(N^2)了一遍。
// worst case O(N^2)
// on average O(N)
// 补充一个产生随机数
// import java.util.Random;
// Random random = new Random();
// random.nextInt(right - left); 会 产生一个 [0, right - left - 1] 的数字。

```
