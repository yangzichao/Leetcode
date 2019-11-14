# 275J. H-Index II

https://leetcode.com/problems/h-index-ii/

## Binary Search
想法是这样的 因为是sorted array 可以用
binary search 但是注意这些 n - pivot 和 n - left 是需要好好想的。
最好的办法是要改进这些判断，首先判断相等，然后再判断别的。
可以确定的是 最后一定是返回 n - left;
为什么？
因为L一定是停在交叉点右侧的。而P却可能是在任何一侧。
同样的，R 一定在交叉点左侧，所以返回 n - right - 1 也是可以通过的。
```Java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        int pivot = 0;
        while( left <= right){
            pivot = left + (right - left)/2;
            if(citations[pivot] < (n - pivot)) {
                left = pivot + 1;
            }else if(citations[pivot] > (n - pivot)){
                right = pivot - 1;
            }else{
                return n - pivot;
            }
        }
        return n - left;
    }
}
```

第二次写的

```Java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        int pivot = 0;
        while( left <= right){
            pivot = left + (right - left)/2;
            if(citations[pivot] == n - pivot){
                return n - pivot;
            }else if(citations[pivot] < n - pivot){
                left = pivot + 1;
            }else{
                right = pivot - 1;
            }
        }
        return n - left;
    }
}
```
