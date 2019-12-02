# 074J. Search a 2D Matrix
https://leetcode.com/problems/search-a-2d-matrix/


## Method Best

这个题值得学习的地方，就是用 /n取行号，用%n取列号。

```Java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length < 1){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m*n - 1; // 为何还是 mn - 1呢 因为 (mn-1)%n = n - 1而不是0。
        int pivot = 0;
        while(left <= right){
            pivot = left + (right - left)/2;
            if( matrix[pivot/n][pivot%n] < target){
                left = pivot + 1;
            }else if ( matrix[pivot/n][pivot%n] > target){
                right = pivot - 1;
            }else{
                return true;
            }
        }
        return false;
    }
}
```
