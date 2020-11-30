# 240J. Search a 2D Matrix II

https://leetcode.com/problems/search-a-2d-matrix-ii/

## Method Best: 都在备注中

站在右上角看。这个矩阵其实就像是一个Binary Search Tree.

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if(m < 1){
            return false;
        }
        int n = matrix[0].length;
        int right = n - 1;
        int up = 0;
        while(up < m && right >= 0){
            if(matrix[up][right] == target){
                return true;
            }else if ( matrix[up][right] < target ){
                up++;
            }else{
                right--;
            }
        }
        return false;
    }
}
```
当然站在左下角往上看也是的。

```Java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length < 1 || matrix[0].length < 1){
            return false;
        }
        // 由于这个矩阵是上下和左右都有序的，比较巧。
        //一个点左上的矩阵肯定都比他小，右下的矩阵都比他大
        // 如果该点比target小，那么左上的子矩阵都被target小，不用比了，left++
        // 如果该点比target大，那么右下的子矩阵都比target大，不用比了，down++
        // 因此会逐渐由左下迫近到右上。
        // 这个像是61b的多维数据查找。
        // 所以可以用如下的方法
        int left = 0;
        int down = matrix.length - 1;
        while(left < matrix[0].length && down > -1){
            if(matrix[down][left] < target){
                left++;
            }else if (matrix[down][left] > target){
                down--;
            }else{
                return true;
            }
        }
        return false;
    }
}
```
