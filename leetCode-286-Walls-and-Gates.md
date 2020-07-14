# 286 Walls-and-Gates

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
<div><p>You are given a <i>m x n</i> 2D grid initialized with these three possible values.</p>
<ol>
	<li><code>-1</code> - A wall or an obstacle.</li>
	<li><code>0</code> - A gate.</li>
	<li><code>INF</code> - Infinity means an empty room. We use the value <code>2<sup>31</sup> - 1 = 2147483647</code> to represent <code>INF</code> as you may assume that the distance to a gate is less than <code>2147483647</code>.</li>
</ol>
<p>Fill each empty room with the distance to its <i>nearest</i> gate. If it is impossible to reach a gate, it should be filled with <code>INF</code>.</p>
<p><strong>Example:&nbsp;</strong></p>
<p>Given the 2D grid:</p>
<pre>INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
</pre>
<p>After running your function, the 2D grid should be:</p>
<pre>  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private int rows;
    private int columns;
    // 这个题，我可以不用 marked, 因为只要不是 INF, 肯定都是不能去的。
    // 这个题很高级，多点同时BFS，给力
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length < 1) return;
        this.rows = rooms.length;
        this.columns = rooms[0].length;
        
        Queue<Integer> bfsQueue =  new LinkedList<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(rooms[i][j] != 0) {
                    continue;
                }
                bfsQueue.offer(i*columns + j);
            }
        }

        while(!bfsQueue.isEmpty()){
            int size = bfsQueue.size();
            while(size > 0) {
                size -= 1;
                int curId = bfsQueue.poll();
                int curRow = curId/columns;
                int curCol = curId%columns;


                if(curRow > 0 && isNotMarked(rooms, curRow - 1, curCol) ){
                    bfsQueue.offer( (curRow - 1)* columns + curCol );
                    rooms[curRow - 1][curCol] = rooms[curRow][curCol] + 1;
                }

                if(curRow + 1 < rows && isNotMarked(rooms, curRow + 1, curCol) ) {
                    bfsQueue.offer( (curRow + 1)* columns + curCol );
                    rooms[curRow + 1][curCol] = rooms[curRow][curCol] + 1;
                }

                if(curCol > 0 && isNotMarked(rooms, curRow, curCol - 1) ){
                    bfsQueue.offer( curRow * columns + curCol - 1);
                    rooms[curRow][curCol - 1] = rooms[curRow][curCol] + 1;
                }

                if(curCol + 1 < columns && isNotMarked(rooms, curRow , curCol + 1) ) {
                    bfsQueue.offer( curRow * columns + curCol + 1);
                    rooms[curRow][curCol + 1] = rooms[curRow][curCol] + 1;
                }
            }

        }

        return;
    }


    public boolean isNotMarked(int[][] rooms, int row, int col) {
        int curVal = rooms[row][col];
        return curVal == Integer.MAX_VALUE;
    }

}
​

```

```
