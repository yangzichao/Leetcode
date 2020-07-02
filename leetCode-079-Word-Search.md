# 079J Word Search

https://leetcode.com/problems/word-search/

这是一个很不一样的回溯。我直觉是 dfs。但是发现应当回溯。
我的答案逻辑是，首先找到一个开始的点。然后 dfs。如果 dfs
每前进一层，能够匹配下一个字符。那么我们就 return true.
反之就 return false. 现在有问题了: dfs 是需要毁灭性的
标记访问路径的。如果一个起点 return false，那么我们需要
想办法退回去的。朴素的思路就是，如果四个方向皆碰壁，那么就
意味着没有一条路是通顺的。此时我们应当回退。

这个题之所以写的比起典型的回溯要不太一样。是因为典型的回溯的图一定需要遍历
全局。而这个题并不需要。我们只需要尝试全部的起点就可以了。

```java
class Solution {
    private int L; // word.length()
    private int M;
    private int N;
    public boolean exist(char[][] board, String word) {
        this.L = word.length();
        // if(word.length == 0) return true;
        this.M = board.length;
        if(M < 1) return false;
        this.N = board[0].length;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int row, int column, int index){
        if(index == L) return true;
        if( (row < 0) || (row > M - 1) || (column < 0) || (column > N - 1 )) {
            return false;
        }
        if( board[row][column] != word.charAt(index) ) return false;
        char curChar = board[row][column];
        board[row][column] = '.';
        if ( dfs(board,word, row + 1, column, index+1) ||
            dfs(board,word, row - 1, column, index+1) ||
            dfs(board,word, row, column + 1, index+1) ||
            dfs(board,word, row, column - 1, index+1) ){
            return true;
        }
        board[row][column] = curChar;
        return false;
    }
}
```
