# 062J. Unique Paths

https://leetcode.com/problems/unique-paths/

## DP

难得自己想出动态规划。
最重要的是找子状态。
这是一个 2D 的动态规划。
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

一个小优化

```java
class Solution {
    public int uniquePaths(int m, int n) {
        if(m < n) {
            return uniquePaths(n, m);
        }

        int[] temp = new int[n];
        int[] ans  = new int[n];
        for(int i = 0; i < n; i++){
            temp[i] = 1;
            ans[i] = 1;
        }

        for(int j = 1; j < m; j++) {
            for(int i = 0; i < n; i++) {
                if(i == 0) {
                    ans[i] = 1;
                    continue;
                }
                ans[i] = temp[i] + ans[i - 1];
                temp[i] = ans[i];
            }
        }
        return ans[n - 1];
    }
}
```
