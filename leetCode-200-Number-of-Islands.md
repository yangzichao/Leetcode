# 200. Number of Islands
https://leetcode.com/problems/number-of-islands/

## DFS
方法是找到1的话，就用DFS把周围相邻的1都标记成0。
```java
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
          return 0;
        }
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid, int row, int column){
        int m = grid.length;
        int n = grid[0].length;
        if(row < 0||column < 0|| row >= m|| column >= n || grid[row][column] == '0') return;
        grid[row][column] = '0';//必须要先标记为0，否则会影响周围四个点DFS从而报错。
        dfs(grid, row - 1, column);
        dfs(grid, row + 1, column);
        dfs(grid, row, column - 1);
        dfs(grid, row, column + 1);
    }
}
```

## BFS: Best
<pre>
BFS和Queue就很有联系。
这里我们给整个棋盘每个位置都给一个id，id入Queue。
可以想象这里BFS是更好的解法，因为在空间上更省。
</pre>
```java
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
          return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1') {
                    ans++;
                    grid[i][j] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(i*n+j);
                    while(!neighbors.isEmpty()){
                        int id = neighbors.remove();
                        int row = id/n;
                        int col = id%n;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                          neighbors.add((row-1) * n + col);
                          grid[row-1][col] = '0';
                        }
                        if (row + 1 < m && grid[row+1][col] == '1') {
                          neighbors.add((row+1) * n + col);
                          grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                          neighbors.add(row * n + col-1);
                          grid[row][col-1] = '0';
                        }
                        if (col + 1 < n && grid[row][col+1] == '1') {
                          neighbors.add(row * n + col+1);
                          grid[row][col+1] = '0';
                        }
                    }
                }

            }
        }
        return ans;
    }
}
```
