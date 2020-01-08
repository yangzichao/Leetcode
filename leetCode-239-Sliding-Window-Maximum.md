# *239J. Sliding Window Maximum
https://leetcode.com/problems/sliding-window-maximum/

## Method Trivial

分析：
1. 暴力法就是Nk。
2. 除此之外最朴素的思想是用heap. 堆的上方始终是最大的值。堆的大小始终保持k个。
    当滑动窗户的时候。找到前面的元素删除。但是由于入堆一个 log(k),查找并删除又是一个k.
    这里 log(k) 的k 是因为总元素是k。因此用heap其实没啥优势，是Nk.

3. 类似的我们可以用一个TreeMap. 其key是值，value是count。值自动排序了很好理解。


```java

```

## Method Best: Monotonic Queue.

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length < 1) return nums;
        int[] maxs = new int[nums.length - k + 1];
        int count = 0;
        Deque<Integer> window = new LinkedList<>();
        for(int i = 0; i < nums.length; i++){
            while(!window.isEmpty() && nums[i] > nums[window.getLast()] ){
                window.removeLast();
            }
            while(!window.isEmpty() && window.getFirst() < i - k + 1){
                window.removeFirst();
            }
            window.addLast(i);
            if(i >= k - 1){
                maxs[count++] = nums[window.getFirst()];
            } 
        }
        return maxs;
    }
}
```