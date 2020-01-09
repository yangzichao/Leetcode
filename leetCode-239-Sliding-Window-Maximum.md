# *239J. Sliding Window Maximum
https://leetcode.com/problems/sliding-window-maximum/

## Method Trivial
分析：
1. 暴力法就是O(Nk)。
2. 除此之外最朴素的思想是用heap. 堆的上方始终是最大的值。堆的大小始终保持k个。
    当滑动窗户的时候。找到前面的元素删除。但是由于入堆一个 log(k),而随机查找并删除是O(k).
    因此用heap其实没啥优势，是O(Nk).

改进：    
   达到类似的效果，我们可以用一个TreeMap. 其key是值，value是count。值自动排序了很好理解。
   TreeMap插入是 log(k),删除是 log(k), 因此是 O(Nlogk).提升了一些。
   
## Method Best: Monotonic Queue.

<pre>
   从上面朴素的思想里想到，注意到其实我们需要的就是一个数据结构，其结构顶是最大值。
   而删除的原则和sliding window相同。从sliding window的经验中我们可以想到，
   单调递减队列, monotonic decreasing queue 就是这样的数据结构。
</pre>

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