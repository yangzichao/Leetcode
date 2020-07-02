# 051J. N-Queens

https://leetcode.com/problems/n-queens/

N-皇后虽然经典，但是我也挺牛的。

以下是一个非常直白的模板写法。很经典。
但是速度不是最快的。
提升办法之一是先建立完整的棋盘，这样放置一个 queen 的时候，就可以排除掉
非常多的位置。避免了重复的运算。这就是一个以空间换效率的 dp。这也是官方
的解法。所以，如果想提升，不如用这个官方的例子来搞定。

```java
class Solution {
    private List<List<String>> collectAns;
    private List<String> tempAns;
    private boolean[] numSet;
    private int N;
    public List<List<String>> solveNQueens(int n) {
        this.collectAns = new ArrayList<>();
        this.tempAns = new ArrayList<>();
        this.numSet = new boolean[9];
        this.N = n;
        backtracker(0);
        return collectAns;
    }
    private void backtracker(int n){
        if(n == N){
            collectAns.add(new ArrayList<String>(tempAns));
            return;
        }
        for(int i = 0; i < N ; i++){
            if(numSet[i]) continue;
            if(!diagChecker(n,i)) continue;
            numSet[i] = true;
            tempAns.add(rowBuilder(i));
            backtracker(n+1);
            tempAns.remove(tempAns.size() - 1);
            numSet[i] = false;
        }
        return;
    }
    private boolean diagChecker(int row, int column){
        for(int r = 1; r < row+1; r++){
            if(  ( (column - r > -1 ) &&  tempAns.get(row - r).charAt(column - r) == 'Q')
                || ( (column + r < N ) && tempAns.get(row - r).charAt(column + r) == 'Q' )  ){
                return false;
            }
        }
        return true;
    }
    private String rowBuilder(int index){
        String row = "";
        for(int i = 0; i < N; i++){
            row += i == index ? "Q" : ".";
        }
        return row;
    }
}
```
