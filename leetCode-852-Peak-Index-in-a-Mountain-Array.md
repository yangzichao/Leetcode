# 852J. Peak Index in a Mountain Array
https://leetcode.com/problems/peak-index-in-a-mountain-array/
Binary Search So easy;
进阶：
162
究极：
1095
## Method Best: Binary Search

```java
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length -1;
        int pivot = 0;
        while(left <= right){
            pivot = left + (right  - left)/2;
            if( (A[pivot] > A[pivot + 1]) &&(A[pivot] > A[pivot - 1])){
                return pivot;
            }else if(A[pivot] < A[pivot + 1]){
                left = pivot + 1;
            }else{
                right = pivot - 1;
            }
        }
        return pivot;
    }
}
```
