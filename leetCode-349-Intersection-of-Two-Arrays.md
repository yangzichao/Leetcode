# 349J. Intersection of Two Arrays
https://leetcode.com/problems/intersection-of-two-arrays/

提要：Two Sets 大法
## Method 1: Two Sets
先将两个数组分别加入两个Sets
然后运用java Sets自带的 retainAll 方法可以选择重合的部分。

```Java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for(int i = 0; i < nums1.length; i++){
            set1.add(nums1[i]);
        }
        for(int i = 0; i < nums2.length; i++){
            set2.add(nums2[i]);
        }

        set1.retainAll(set2);
        int[] re = new int[set1.size()];
        int i = 0;
        for(int n : set1){
            re[i] = n;
            i++;
        }
        return re;
    }
}
```
改进：
效果相同，只是用循环 contains()方法来判断是否有重合的，较繁琐。
