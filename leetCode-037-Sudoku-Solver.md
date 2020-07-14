# 037J. Sudoku Solver

https://leetcode.com/problems/sudoku-solver/

## Method Best

<pre>
某种程度上来说，这个题比上一题还简单点。真是会者不难，难者不会。
目前还是菜鸡，Backtracking好写但是难想，而且想到用BackTracking
就挺有技术难度的。

大致的想法就是，从棋盘左上到右下遍历，遇到已经有数字的就跳过，然后
对空位尝试所有的可能。当然每次尝试也都需要符合数独的规则，由此可以想到
至少需要写一个判定该位字符是否符合数独规则的函数。而这个函数比较好写，
输入应当是现在的棋盘实例，当下的行列数和测试的值。剩下的都在code中。
不论如何这个题猛一遇到还是蛮难的。
</pre>

```java
class Solution {
    public void solveSudoku(char[][] board) {
        // Suppose Sudoku Board is promised to have at least one solution.
        scanner(board, 0, 0); // Start from the (0,0)
    }
    // scanner is a helper function
    // 规定从左到右，自上而下的检查数独棋盘
    public boolean scanner(char[][] board, int row, int column){
        // we have reached the end of the board, return true；
        if(row == 9) return true;
        // if we've reached the end of one row, go to the beginning of next row.
        // 如果后面错我们就继承这个错向上传递，对了也一样。
        if(column == 9 ) return scanner(board, row + 1, 0);
        // if it is taken by some number already, check next position
        // 同上
        if(board[row][column] != '.') return scanner(board, row, column + 1);
        // edge cases都考虑了，开始穷举
        // try 1 - 9
        for(char c = '1'; c <= '9'; c++){
            //最起码要符合当下的数独形势，不行就换下一个试试
            // 注意此时棋盘上该位置仍为空
            if(!validOrNot(board,row,column,c)) continue;
            // 如果暂时还行就先赋值试试
            board[row][column] = c;
            // 赋值之后向后传递，如果直到最后都测试成功，那么就向上返回true。
            if(scanner(board,row,column+1)) return true;
            // 如果到最后没有测试成功，赶快把这位置复原成空，下一次循环再赋值一个新的试试
            board[row][column] = '.';
        }
        //如果从1-9都试了，都不行，那么就告诉上一级，你们前面哪里搞错了，试试别的。
        return false;
    }

    // try to put c at (m,n), check if it is valid.
    public boolean validOrNot(char[][] board, int row, int column, char c){
        // 由于现在c还没有赋值给该位置，可以放心大胆的对比。
        // 检查三个部分，row, column, sub-box，是否出现过该c.

        //检查row 和 column
        for(int i = 0; i < 9; i++){
            if( board[row][i] == c || board[i][column] == c) return false;
        }

        // sub-box的左上角位置 (m,n)
        int m = row - row%3, n = column - column%3;
        // 检查 sub-box
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[m+i][n+j] == c) return false;
            }
        }
        //检查完了 没啥问题 返回true吧
        return true;

    }
}
```

还可以为每行每列每个 box 都建立一个 Set. 注意这个 Set 一定要先扫一遍放进去，不然会犯错。

```java
class Solution {
    boolean[][] rowSets;
    boolean[][] colSets;
    boolean[][] boxSets;

    public void solveSudoku(char[][] board) {
        rowSets = new boolean[9][10];
        colSets = new boolean[9][10];
        boxSets = new boolean[9][10];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if( board[i][j] == '.') continue;
                int number = board[i][j] - '0';
                int box = i/3*3 + j/3;
                rowSets[i][number] = true;
                colSets[j][number] = true;
                boxSets[box][number] = true;
            }
        }
        scanner(board, 0, 0);
    }
    public boolean scanner(char[][] board, int row, int col){
        if(row == 9) return true;
        if(col == 9 ) return scanner(board, row + 1, 0);
        if(board[row][col] != '.') return scanner(board, row, col + 1);

        int box = row/3 * 3 + col/3;

        for(char c = '1'; c <= '9'; c++){
            int number = c - '0';
            if(rowSets[row][number] || colSets[col][number] || boxSets[box][number]) continue;

            board[row][col] = c;
            rowSets[row][number] = true;
            colSets[col][number] = true;
            boxSets[box][number] = true;

            if(scanner(board,row,col+1)) return true;

            board[row][col] = '.';
            rowSets[row][number] = false;
            colSets[col][number] = false;
            boxSets[box][number] = false;
        }
        return false;
    }
}
```
