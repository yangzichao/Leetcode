# 059J. Spiral Matrix II
https://leetcode.com/problems/spiral-matrix-ii/

## 同054
```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int[] dr = new int[]{0,1,0,-1};
        int[] dc = new int[]{1,0,-1,0};
        int d = 0;
        int r = 0;
        int c = 0;
        for(int i = 1; i < n*n + 1; i++){
            ans[r][c] = i;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr >= 0 && nc >= 0 && nr < n && nc < n && ans[nr][nc] == 0){
                r = nr;
                c = nc;
            }else{
                d = (d + 1)%4;
                r += dr[d];
                c += dc[d];
            }
        }
        return ans;
    }
}
```