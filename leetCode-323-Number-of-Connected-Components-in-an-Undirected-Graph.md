# 323J. Number of Connected Components in an Undirected Graph

https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

这个题用Union Find 比较好，为什么呢？因为还未成图。
用Union Find 不需要重新建图。
## Method DFS/ CC
```java
class Solution {
    // 这个题直接用261的代码就好了
    // Graph是Tree，首先要只有一个部分，其次不能有环。
    // count 数有几个部分
    // hasCycle 判断是否有环
    private int count;
    private boolean hasCycle;
    private boolean[] visited;
    
    public int countComponents(int n, int[][] edges) {
        // Initialize Global Variables
        this.count = 0;
        this.visited = new boolean[n];
        this.hasCycle = false;
        // Initialize Graph
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for(int[] pairs : edges){
            adj[pairs[0]].add(pairs[1]);
            adj[pairs[1]].add(pairs[0]);
        }
        // DFS
        for(int i = 0; i < n; i++){
            if(!this.visited[i]){
                dfs(adj,i,i);
                count++;
            }
        }
        // Graph是Tree，首先要只有一个部分，其次不能有环。
        return count;       
    }
    private void dfs(List<Integer>[] adj, int cur, int last ){
        visited[cur] = true;
        for(int next : adj[cur]){
            if(!visited[next]){
                dfs(adj, next, cur);
            }else if(next != last){
                this.hasCycle = true;
            }
        }
    }    
}
```