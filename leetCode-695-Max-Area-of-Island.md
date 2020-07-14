# 695 Max-Area-of-Island

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
<div><p>Given a non-empty 2D array <code>grid</code> of 0's and 1's, an <b>island</b> is a group of <code>1</code>'s (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.</p>
<p>Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)</p>
<p><b>Example 1:</b></p>
<pre>[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,<b>1</b>,0,<b>1</b>,0,0],
 [0,1,0,0,1,1,0,0,<b>1</b>,<b>1</b>,<b>1</b>,0,0],
 [0,0,0,0,0,0,0,0,0,0,<b>1</b>,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
</pre>
Given the above grid, return <code>6</code>. Note the answer is not 11, because the island must be connected 4-directionally.
<p><b>Example 2:</b></p>
<pre>[[0,0,0,0,0,0,0,0]]</pre>
Given the above grid, return <code>0</code>.
<p><b>Note:</b> The length of each dimension in the given <code>grid</code> does not exceed 50.</p>
</div></section>
 
 ## Method One 
 
``` Java

class Solution {
   public int maxAreaOfIsland(int[][] grid) {
       if(grid == null || grid.length < 1) {
           return 0;
      }
       int M = grid.length; // Number of rows
       int N = grid[0].length; // Number of cols

int maxArea = 0;

for(int i = 0; i < M; i++) {
           for( int j = 0; j < N; j++ ) {
               if(grid[i][j] == 0) continue;
               int curArea = 0;
               Queue<Integer> ids = new LinkedList<>();
               ids.offer(i\*N + j );
               while(!ids.isEmpty()) {
                   int size = ids.size();
                   while(size > 0) {
                       size--;

int id = ids.poll();
                       int row = id/N;
                       int col = id%N;

if( grid[row][col] == 1) {
                           curArea += 1;
                           grid[row][col] = 0;
                      }else{
                           continue;
                      }
                       if( row - 1 >= 0 ) {
                           ids.offer( (row - 1)*N + col);
                      }
                       if( row + 1 < M) {
                           ids.offer( (row + 1)*N + col);
                      }
                       if( col - 1 >= 0) {
                           ids.offer( row* N + col - 1);
                      }
                       if( col + 1 < N) {
                           ids.offer( row*N + col + 1);
                      }
                  }
              }
               if(curArea > maxArea) {
                   maxArea = curArea;
              }
          }
      }
       return maxArea;
  }
​

```

```
