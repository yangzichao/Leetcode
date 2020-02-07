# 062J. Unique Paths
https://leetcode.com/problems/unique-paths/

## DP
难得自己想出动态规划。
最重要的是找子状态。
这是一个2D的动态规划。
key observation is :
每个位置上的路径，等于他上面位置和左面位置路径数之和。

这个代码还可以被进一步优化空间。
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
```