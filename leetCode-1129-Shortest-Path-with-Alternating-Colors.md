# 1129 Shortest-Path-with-Alternating-Colors 
 
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
<div><p>Consider a directed graph, with nodes labelled <code>0, 1, ..., n-1</code>.&nbsp; In this graph, each edge is either red or blue, and there could&nbsp;be self-edges or parallel edges.</p>
<p>Each <code>[i, j]</code> in <code>red_edges</code> denotes a red directed edge from node <code>i</code> to node <code>j</code>.&nbsp; Similarly, each <code>[i, j]</code> in <code>blue_edges</code> denotes a blue directed edge from node <code>i</code> to node <code>j</code>.</p>
<p>Return an array <code>answer</code>&nbsp;of length <code>n</code>,&nbsp;where each&nbsp;<code>answer[X]</code>&nbsp;is&nbsp;the length of the shortest path from node <code>0</code>&nbsp;to node <code>X</code>&nbsp;such that the edge colors alternate along the path (or <code>-1</code> if such a path doesn't exist).</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
<strong>Output:</strong> [0,1,-1]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
<strong>Output:</strong> [0,1,-1]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
<strong>Output:</strong> [0,-1,-1]
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
<strong>Output:</strong> [0,1,2]
</pre><p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong> n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
<strong>Output:</strong> [0,1,1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>red_edges.length &lt;= 400</code></li>
	<li><code>blue_edges.length &lt;= 400</code></li>
	<li><code>red_edges[i].length == blue_edges[i].length == 2</code></li>
	<li><code>0 &lt;= red_edges[i][j], blue_edges[i][j] &lt; n</code></li>
</ul></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 1129 
        // 想法就是先选红边和先选蓝边出发，用BFS 然后 ans[] 储存最短的路径。
        // tricky 的地方是 红边和蓝边要用两个 set 来标记访问过没有。也就是一个点可以被红蓝各访问一次。
        
        int[] ans = new int[n];
        Arrays.fill(ans, - 1);
        Map<Integer, List<Integer> >redGraph = new HashMap<>();
        Map<Integer, List<Integer> >blueGraph = new HashMap<>();
        
        // TODO: refactor
        for(int[] redEdge : redEdges ){
            redGraph.putIfAbsent( redEdge[0], new ArrayList<>());
            redGraph.get(redEdge[0]).add(redEdge[1]);
        }
        for(int[] blueEdge: blueEdges ){
            blueGraph.putIfAbsent( blueEdge[0], new ArrayList<>());
            blueGraph.get(blueEdge[0]).add(blueEdge[1]); 
        }
        
        
        LinkedList<Integer> bQ= new LinkedList<>();
        LinkedList<Integer> rQ= new LinkedList<>();
        bQ.offer(0);
        rQ.offer(0);
        
        int level = 0;
        Set<Integer> blueSet = new HashSet<>();
        Set<Integer> redSet = new HashSet<>();
        
        while(!bQ.isEmpty()){
            int size = bQ.size();
            while( size > 0 ){
                size -= 1;
                int curB = bQ.poll();
                
                ans[curB] = ans[curB] < 0 ? level : Math.min(ans[curB], level);
                Map<Integer, List<Integer> > nextBGraph = level % 2 == 0 ? blueGraph : redGraph ;
                Set<Integer> nextSet = level % 2 == 0 ? blueSet : redSet;
                Set<Integer> curSet = level % 2 == 1 ? blueSet : redSet;
                curSet.add(curB);
                for(int nextB : nextBGraph.getOrDefault(curB, new ArrayList<>()) ){
                    if(nextSet.contains(nextB)){
                        continue;
                    }
                    bQ.offer(nextB);
                }
            }
            level += 1;
        }
        
        level = 0;
        blueSet = new HashSet<>();
        redSet = new HashSet<>();
        while(!rQ.isEmpty()){
            int size = rQ.size();
            while( size > 0 ){
                size -= 1;
                int curR = rQ.poll();
                ans[curR] = ans[curR] < 0 ? level : Math.min(ans[curR], level);
                Map<Integer, List<Integer> > nextRGraph = level % 2 == 1 ? blueGraph : redGraph ;
                Set<Integer> nextSet = level % 2 == 1 ? blueSet : redSet;
                Set<Integer> curSet = level % 2 == 0 ? blueSet : redSet;
                curSet.add(curR);
                for(int nextR : nextRGraph.getOrDefault(curR, new ArrayList<>()) ){
                    if(nextSet.contains(nextR)){
                        continue;
                    }
                    rQ.offer(nextR);
                }
            }
            level += 1;
        }
        return ans;
    }
}
​
```

with refactor

```java
class Solution {
    private Map<Integer, List<Integer> >redGraph;
    private Map<Integer, List<Integer> >blueGraph;
    private int[] ans;
    private final String BLUE = "blue";
    private final String RED = "red";
    
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        this.ans = new int[n];
        Arrays.fill(ans, -1);
        this.redGraph =  buildGraph(redEdges);
        this.blueGraph =  buildGraph(blueEdges);
        bfs(BLUE);
        bfs(RED);
        return ans;
    }
    public Map<Integer, List<Integer> > buildGraph(int[][] edges){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges ){
            graph.putIfAbsent( edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }
    public void bfs(String initColor ){
        int TOGGLER = initColor.equals(BLUE) ? 0 : 1;
        
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int level = 0;
        Set<Integer> blueSet = new HashSet<>();
        Set<Integer> redSet = new HashSet<>();

        while(!queue.isEmpty()){
            int size = queue.size();
            while( size > 0 ){
                size -= 1;
                int cur = queue.poll();

                ans[cur] = ans[cur] < 0 ? level : Math.min(ans[cur], level);
                
                Map<Integer, List<Integer> > nextGraph = level % 2 == TOGGLER ? blueGraph : redGraph ;
                Set<Integer> nextSet = level % 2 == TOGGLER ? blueSet : redSet;
                Set<Integer> curSet = level % 2 != TOGGLER ? blueSet : redSet;
                curSet.add(cur);
                for(int next : nextGraph.getOrDefault(cur, new ArrayList<>()) ){
                    if(nextSet.contains(next)){
                        continue;
                    }
                    queue.offer(next);
                }
            }
            level += 1;
        }
    }
}
```