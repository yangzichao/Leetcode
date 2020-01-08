# UnionFind

什么时候必须要用UnionFind?
当图的数据是一点一点传递进来的时候。
什么时候可以选用UnionFind?
当图的数据整个提供给我们的时候，我们用DFS
或者BFS查找相连的部分会更快。

## LC 


### LC 强相关
* [323]()是用UnionFind比较好的题
* [128]() 虽然有一个ad hoc的解，但是这个题比较适合UnionFind


[305]() 二维 UnionFind 就是让你用UnionFind 


### LC弱相关
可以用UnionFind 解 但是不必要的
写了的：
* [261]()




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