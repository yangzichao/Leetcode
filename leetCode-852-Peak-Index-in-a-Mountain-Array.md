# 852J. Peak Index in a Mountain Array
https://leetcode.com/problems/peak-index-in-a-mountain-array/
Binary Search So easy;
进阶：
162
究极：
1095
## Method Best: Binary Search
这个题的难点在于，如何避免 pivot - 1 overflow 的问题。       
由于 pivot 永远不会取到 nums.length - 1, 所以  pivot + 1 是无论如何安全的。
那么我们就只利用 pivot + 1, 然后用 else 把 pivot - 1 的各种情况都包括进去。       
这就是巧妙之处。         
不信你可以试着用 pivot - 1 来试试，一定翻车。      
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
