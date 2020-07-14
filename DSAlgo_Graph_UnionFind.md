# UnionFind

什么时候必须要用 UnionFind?
当图的数据是一点一点传递进来的时候。
什么时候可以选用 UnionFind?
当图的数据整个提供给我们的时候，我们用 DFS
或者 BFS 查找相连的部分会更快。
什么时候用 UnionFind with path compression ？
当图建好之后，查找一个东西，历经好几层，可以 path compression 然后让他下次更快。

## LC

### LC 强相关

- [323]()是用 UnionFind 比较好的题
- [128]() 虽然有一个 ad hoc 的解，但是这个题比较适合 UnionFind

* [399: Evaluate-Division](https://leetcode.com/problems/evaluate-division/) 399 其实是很好的用 UnionFind 的题，而且可以 with path compression
  [399. Evaluate-Division](leetCode-399-Evaluate-Division.md)

  [305]() 二维 UnionFind 就是让你用 UnionFind

### LC 弱相关

可以用 UnionFind 解 但是不必要的
写了的：

- [261]()

## Java 实现

### 2-D Union Find 为例

以 [200](leetCode-200-Number-of-Islands.md) 为例
2-D Union Find

```java
class Solution {

    class UnionFind{
        int count;
        int[] parent;
        int[] size;

        public UnionFind(char[][] grid){ // for 2D char grid

            int M = grid.length;
            int N = grid[0].length;
            count = 0;
            parent = new int[M*N];
            size   = new int[M*N];
            for(int i = 0; i < M; i++){
                for(int j = 0; j < N; j++){
                    // 这个题中 0 是边界，不需要UnionFind.
                    if(grid[i][j] == '1'){
                        parent[i*N +j] = i*N + j;
                        size[i*N+j] = 1;
                        count+=1;
                    }
                }
            }
        }
        public int findroot(int id){
            while(parent[id] != id){
                id = parent[id];
            }
            return id;
        }

        public void union(int x, int y){
            int rootx = findroot(x);
            int rooty = findroot(y);
            if(rootx == rooty){
                return;
            }

            if(size[rootx] > size[rooty]){
                parent[rooty] = rootx;
                size[rootx] += size[rooty];
            }else{
                parent[rootx] = rooty;
                size[rooty] += size[rootx];
            }
            count--;
        }
        public int getCount(){
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;
        int numOfIslands = 0;
        UnionFind uf = new UnionFind(grid);
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(grid[i][j] != '1') continue;
                grid[i][j] = '0';
                int curId = i*N + j;
                if(i > 0 && grid[i - 1][j] == '1'){
                    uf.union(curId, curId - N);
                }
                if(i < M - 1 && grid[i + 1][j] == '1'){
                    uf.union(curId, curId + N);
                }
                if(j > 0 && grid[i][j - 1] == '1'){
                    uf.union(curId, curId - 1);
                }
                if(j < N -1 && grid[i][j + 1] == '1'){
                    uf.union(curId, curId + 1);
                }
            }
        }
        return uf.getCount();
    }
}
```
