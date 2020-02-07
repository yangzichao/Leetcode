# 063J. Unique Paths II
https://leetcode.com/problems/unique-paths-ii/

## Method DP
这个题和上一题的观察一样。但是不同的是，有障碍.
首先我们让遇到障碍的时候，它的值变成0。这样做动态规划的时候，
从山这一侧的道路总数就变成了0.
其次我们让路线数量变为负数。这样就和山区别开来了。

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1) return 0;
        obstacleGrid[0][0] = -1;
        for(int i = 1; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                obstacleGrid[i][0] = 0;                    
                continue;
            }            
             obstacleGrid[i][0] = obstacleGrid[i-1][0];
        }
        for(int i = 1; i < n; i++){
            if(obstacleGrid[0][i] == 1){
                obstacleGrid[0][i] = 0;                    
                continue;
            }
             obstacleGrid[0][i] = obstacleGrid[0][i-1];
        }        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;                    
                    continue;
                } 
                obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
        return -obstacleGrid[m-1][n-1];
    }
}
```

上面的代码被优化合并成了如下代码。

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
 
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;                    
                    continue;
                }
                if(i < 1 && j < 1){
                   obstacleGrid[0][0] = -1;   
                }else if(i < 1){
                   obstacleGrid[0][j] = obstacleGrid[0][j-1];
                }else if( j < 1){
                   obstacleGrid[i][0] = obstacleGrid[i-1][0];  
                }else{
                   obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1]; 
                }
            }
        }
        return -obstacleGrid[m-1][n-1];
    }
}
```