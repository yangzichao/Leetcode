# 407J. Trapping Rain Water II
https://leetcode.com/problems/trapping-rain-water-ii/


## Method BFS/DFS:
Worst Case:
全部入PQ.再出。
O(MN). 系数4.
```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        /**
        * 
        思路如下：

        木桶原理?：
            存多少水取决于最短的板。
            说人话：水取决于 contour 的最低的的位置
        1D、2D的Contour?:
            1D contour 是两个点，2D 是一个闭合环线。
        如何找contour?：
            先找全局最大contour.
            BFS/DFS 以更新contour.  
        如何保证一直从当下最短板开始搜索？：
            把contour 加入一个按高度排序的 PQ 即可。
            这是一个 优先队列+BFS 而非传统的队列+BFS。
        为什么是BFS而不是DFS？：
            DFS可以行，但是PQ天然的Q属性，让我们不必要去
            建立一个额外的 stack 来 PQ + Stack 强行DFS.
        如何判断水容量？:
            BFS如果遇到比自己低的位置就可以直接判断加水。
            为什么？因为我们就是从最短的木桶边开始搜索的，
            其他的contour都比自己高。对于这一点来说，它
            所能储的水的上限，就是此刻。以后也不会被更新。
            如果BFS遇到比自己高的位置，把它加入contour.
        */
        
        // Initialize Graph.
        int M = heightMap.length;
        if(M < 3) return 0;
        int N = heightMap[0].length;
        if(N < 3) return 0;
        boolean[] visited = new boolean[M*N];
        
        // Initialize heap and water volume
        PriorityQueue<Integer> contour = new PriorityQueue<>((a,b) -> {
            return heightMap[a/N][a%N] - heightMap[b/N][b%N];
        });
        int volume = 0;
        
        // Initialize boundary.
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if( i == 0 || i == M - 1 || j == 0 || j == N - 1){
                    contour.offer(i*N + j);
                    visited[ i*N + j ] = true;
                }
            }
        }
        // Initialize BFS directions.
        int[] directions = new int[]{1,-1,N,-N}; // right left down up
        // BFS
        while(!contour.isEmpty()){
            // current position and height
            int curId = contour.poll(); 
            int curR  = curId/N;
            int curC  = curId%N;
            int curH  = heightMap[curR][curC];
            for(int d : directions){
                // Next position and height
                int nextId = curId + d;
                int nextR  = nextId/N;
                int nextC  = nextId%N;
                
                // Boundary conditions
                if(nextR < 0 || nextC < 0 || nextR >= M || nextC >= N || visited[nextId]){
                    continue;
                }
                int nextH  = heightMap[nextR][nextC];
                //  便于理解的code
                // if( heights[nextId] < heights[curID]){
                //     volume += heights[curID] - heights[nextId];
                //     heights[nextId] = heights[curID];
                //     visited[nextId] = true;
                //     contour.offer(nextId);
                // }else{
                //     visited[nextId] = true;
                //     contour.offer(nextId);
                // }
                
                // 一简版 code 
                // if( nextH < curH ){                  
                //     volume += curH - nextH;
                //     heightMap[nextR][nextC] = curH;
                // }
                // visited[nextId] = true;
                // contour.offer(nextId);  
                
                // 二简版 code
                volume += Math.max(0,curH - nextH);
                heightMap[nextR][nextC] =  Math.max(curH,nextH);
                visited[nextId] = true;
                contour.offer(nextId);   
            }   
        }
        
        return volume;
    }
}
```

清爽版 code 

```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if(heightMap.length < 3) return 0;
        int M = heightMap.length, N = heightMap[0].length;
        boolean[] visited = new boolean[M*N];
        PriorityQueue<Integer> contour = new PriorityQueue<>((a,b) -> {return heightMap[a/N][a%N] - heightMap[b/N][b%N];});
        int volume = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if( i == 0 || i == M - 1 || j == 0 || j == N - 1){
                    contour.offer(i*N + j);
                    visited[ i*N + j ] = true;
                }
            }
        }
        int[] directions = new int[]{1,-1,N,-N}; 
        while(!contour.isEmpty()){
            int curId = contour.poll(), curR  = curId/N, curC  = curId%N, curH  = heightMap[curR][curC];
            for(int d : directions){
                int nextId = curId + d, nextR  = nextId/N, nextC  = nextId%N;
                if(nextR < 0 || nextC < 0 || nextR >= M || nextC >= N || visited[nextId]) continue;
                int nextH  = heightMap[nextR][nextC];
                volume += Math.max(0,curH - nextH);
                heightMap[nextR][nextC] =  Math.max(curH,nextH);
                visited[nextId] = true;
                contour.offer(nextId);   
            }   
        }
        return volume;
    }
}
```


## 思路2 Dijkstra
https://leetcode.com/problems/trapping-rain-water-ii/discuss/89460/Alternative-approach-using-Dijkstra-in-O(rc-max(log-r-log-c))-time

这个复杂度是 O( MNlog( max(M,N) ) ). 因此若最大变长小于2^4 = 16，这个方法应当更快。