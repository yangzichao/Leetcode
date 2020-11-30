# 994 Rotting-Oranges 
 
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
<div><p>In a given grid, each cell can have one of three&nbsp;values:</p>
<ul>
	<li>the value <code>0</code> representing an empty cell;</li>
	<li>the value <code>1</code> representing a fresh orange;</li>
	<li>the value <code>2</code> representing a rotten orange.</li>
</ul>
<p>Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.</p>
<p>Return the minimum number of minutes that must elapse until no cell has a fresh orange.&nbsp; If this is impossible, return <code>-1</code> instead.</p>
<p>&nbsp;</p>
<div>
<p><strong>Example 1:</strong></p>
<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/02/16/oranges.png" style="width: 712px; height: 150px;"></strong></p>
<pre><strong>Input: </strong><span id="example-input-1-1">[[2,1,1],[1,1,0],[0,1,1]]</span>
<strong>Output: </strong><span id="example-output-1">4</span>
</pre>
<div>
<p><strong>Example 2:</strong></p>
<pre><strong>Input: </strong><span id="example-input-2-1">[[2,1,1],[0,1,1],[1,0,1]]</span>
<strong>Output: </strong><span id="example-output-2">-1</span>
<strong>Explanation: </strong> The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
</pre>
<div>
<p><strong>Example 3:</strong></p>
<pre><strong>Input: </strong><span id="example-input-3-1">[[0,2]]</span>
<strong>Output: </strong><span id="example-output-3">0</span>
<strong>Explanation: </strong> Since there are already no fresh oranges at minute 0, the answer is just 0.
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ol>
	<li><code>1 &lt;= grid.length &lt;= 10</code></li>
	<li><code>1 &lt;= grid[0].length &lt;= 10</code></li>
	<li><code>grid[i][j]</code> is only <code>0</code>, <code>1</code>, or <code>2</code>.</li>
</ol>
</div>
</div>
</div>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        if( rows < 1 ) return 0;
        int columns = grid[0].length;
        
        Queue<int[]> bfsQueue = new LinkedList<>();
        int oranges = 0;
        
        for( int i = 0; i < rows; i++ ) {
            for( int j = 0; j < columns; j++ ) {
                if( grid[i][j] == 1 || grid[i][j] == 2) {
                    oranges += 1;
                }
                if( grid[i][j] != 2 ) {
                    continue;
                }
                bfsQueue.offer( new int[]{i,j} );
            }
        }
        
        if( bfsQueue.size() == 0 ) {
            return oranges == 0 ? 0 : -1;
        } 
        
        int time = -1;
        
        while( !bfsQueue.isEmpty() ) {
            int size = bfsQueue.size();
            while( size > 0 ) {
                size -= 1;
                oranges -= 1;
                int[] cur = bfsQueue.poll();
                int row = cur[0];
                int col = cur[1];
                if( row > 0 && grid[ row - 1 ][ col ] == 1 ) {
                    bfsQueue.offer( new int[]{ row - 1, col } );
                    grid[ row - 1 ][ col ] = 2;
                }
                if( row < rows - 1 && grid[ row + 1 ][ col ] == 1 ) {
                    bfsQueue.offer( new int[]{ row + 1, col });
                    grid[ row + 1 ][ col ] = 2;
                }
                if( col > 0 && grid[ row ][ col - 1 ] == 1 ) {
                    bfsQueue.offer( new int[]{ row, col - 1} );
                    grid[ row ][ col - 1 ] = 2;
                }
                if( col < columns - 1 && grid[ row ][ col + 1 ] == 1 ) {
                    bfsQueue.offer( new int[]{ row, col + 1 });
                    grid[ row ][ col + 1 ] = 2;
                }               
            }
            time += 1;
        }
        return oranges == 0 ? time : -1;
        
    }
}
​
```