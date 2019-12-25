# 088J. Merge Sorted Array
https://leetcode.com/problems/merge-sorted-array/

## Method Best:
这个题有点垃圾。
总之就是从尾巴开始比，这样对于这个题比较有用，因为数组不像
LinkedList比较容易插入。除此之外没啥特别之处。
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while(p1 > -1 || p2 > -1){
            int n1 = p1 > -1? nums1[p1] : Integer.MIN_VALUE;
            int n2 = p2 > -1? nums2[p2] : Integer.MIN_VALUE;
            if(n1 > n2){
                nums1[p--] = n1;
                p1--;
            }else{
                nums1[p--] = n2;
                p2--;
            }  
        }
    }
}
```
