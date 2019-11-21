# 658J. Find K Closest Elements
https://leetcode.com/problems/find-k-closest-elements/
这个题非常机智
Binary Search里非常机智的方法。
## Method Best:
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
