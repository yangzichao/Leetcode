# 658J. Find K Closest Elements
https://leetcode.com/problems/find-k-closest-elements/
这个题非常机智
Binary Search里非常机智的方法。
## Method Best:
**挖槽下面的答案竟然是错的，曾经通过了**
其原理是![下图](./imgs/LC658.JPG)
因此写出了如下的binary search 代码，堪称机智。
```Java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1 - k;

        while(left <= right){
            int pivot = left + (right - left)/2;
            if(Math.abs(arr[pivot] - x) > Math.abs(arr[pivot + k] - x)){
                left = pivot + 1;
            }else{
                right = pivot - 1;
            }
        }
        // System.out.println(left);
        List<Integer> output = new ArrayList<>();
        for(int i = left; i < left + k; i++){
            output.add(arr[i]);
        }
        return output;
    }
}
```

## 正确的
[这个解释也不错](https://leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/)
[参见](https://leetcode.com/problems/find-k-closest-elements/discuss/106426/JavaC%2B%2BPython-Binary-Search-O(log(N-K)-%2B-K))
上面的错误的原因是 比较 abs value.
事实上大致不太挫

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k - 1; 
        // 这里是 length - 1 - k 而不是 length - k, 
        // 是因为 left 可能最终是 right + 1. 所以要留足够的空间。

        while( left <= right ){
            int pivot = left + (right - left)/2;
            // 注意，这里比较的是 pivot 和 pivot + k,
            // 而这里面有 k + 1 个元素，这里的比较是在说，我们是在考虑
            // 删掉 pivot 还是 删掉 pivot + k 构成一个 k 的区间
            // 如果 pivot 的差值更大，我们觉得可以贪心一下
            // 令 left = pivot + 1, 相当于变相把它给删了
            // 如果 pivot + k 的差值更大，我们的 right = pivot - 1 也把它排除出候选范围了。
            int head = x - arr[pivot]  ;
            int tail = arr[pivot + k] - x;
            int diff = head - tail;
            if( diff == 0 ){
                right = pivot - 1;
            }else if ( diff > 0 ) {
                left = pivot + 1;
            }else{
                right = pivot - 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = left; i < left + k; i++ ){
            ans.add(arr[i]);
        } 
        return ans;
    }
}
```