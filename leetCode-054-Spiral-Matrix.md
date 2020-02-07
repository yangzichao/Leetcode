# 054J. Spiral Matrix
https://leetcode.com/problems/spiral-matrix/

## 朴素法
就是纯模拟
遇到边界则依次改变方向。
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int M = matrix.length;
        if(M < 1) return ans;
        int N = matrix[0].length;
        
        boolean[][] seen = new boolean[M][N];
        
        int[] dR = new int[]{0,1,0,-1};
        int[] dC = new int[]{1,0,-1,0};
        
        int r = 0;
        int c = 0;
        int dir = 0;
        
        for(int i = 0; i < M*N; i++){ // i is meaningless, 运行MN次停止
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int nr = r+dR[dir];
            int nc = c+dC[dir];
            if( nr >= 0 && nc >= 0 && nr <= M - 1 && nc <= N - 1 && !seen[nr][nc]){
                r = nr;
                c = nc;
            }else{
                dir = (dir + 1) %4;
                r += dR[dir];
                c += dC[dir];
            }
        }
        return ans;
        
    }
}
```
