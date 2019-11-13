# 275J. H-Index II

https://leetcode.com/problems/h-index-ii/

## Binary Search
想法是这样的

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
