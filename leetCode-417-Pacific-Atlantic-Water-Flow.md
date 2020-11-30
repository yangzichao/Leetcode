# 417 Pacific-Atlantic-Water-Flow

difficulty: Medium

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given an <code>m x n</code> matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.</p>
<p>Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.</p>
<p>Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.</p>
<p><b>Note:</b></p>
<ol>
	<li>The order of returned grid coordinates does not matter.</li>
	<li>Both <i>m</i> and <i>n</i> are less than 150.</li>
</ol>
<p>&nbsp;</p>
<p><b>Example:</b></p>
<pre>Given the following 5x5 matrix:
  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic
Return:
[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
</pre>
<p>&nbsp;</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private int[][] directions = new int[][]{{-1, 0},{1,0},{0,-1},{0,1}}; 
    private int rows;
    private int cols; 
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        // 从太平洋和大西洋的边缘的点开始BFS/DFS。然后找他们并列的地方。
        List<List<Integer>> ans = new ArrayList<>();
        this.rows = matrix.length;
        if( rows < 1 ) {
            return ans;
        }
        this.cols = matrix[0].length;
        
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        
        for(int i = 0 ; i < rows ; i++) {
            pQueue.offer(new int[]{i,0});
            aQueue.offer(new int[]{i,cols - 1});
        }
        for(int j = 0 ; j < cols; j++ ) {
            pQueue.offer(new int[]{0,j});
            aQueue.offer(new int[]{rows - 1, j});
        }
        
        bfs(matrix, pacific, pQueue);
        bfs(matrix, atlantic, aQueue);
        for(int i = 0; i < rows; i++ ) {
            for( int j = 0; j < cols; j++ ) {
                if( pacific[i][j] && atlantic[i][j] ) {
                    List<Integer> pair = new ArrayList<Integer>();
                    pair.add(i);
                    pair.add(j);
                    ans.add(pair);
                }
            }
        }
        return ans;
        
    }
    
    public void bfs(int[][] matrix, boolean[][] marked, Queue<int[]> queue) {
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                size -= 1;
                int[] cur = queue.poll();
                int row = cur[0];
                int col = cur[1];
                marked[row][col] = true;
                for(int[] direction: this.directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];
                    if(nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols 
                       ||  marked[nextRow][nextCol] || matrix[nextRow][nextCol] < matrix[row][col] ) {
                        continue;
                    }
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }
}
```
