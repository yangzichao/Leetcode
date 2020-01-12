# x036J. Valid Sudoku
https://leetcode.com/problems/valid-sudoku/

这个题没啥意思。

最好也不过是可以用int[] 代替map 或者 boolean[] 代替set.

## Method Best

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Integer>[] rows = new HashSet[9];
        Set<Integer>[] cols = new HashSet[9];
        Set<Integer>[] boxes = new HashSet[9];
        for(int i = 0; i < 9; i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();            
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int val = (int) board[i][j] - '0';
                int box = (i/3)*3 + (j/3);
                if(rows[i].contains(val) || cols[j].contains(val) || boxes[box].contains(val)){
                    return false;
                }
                rows[i].add(val);
                cols[j].add(val);
                boxes[box].add(val);
            }
        }
        return true;
    }
}
```