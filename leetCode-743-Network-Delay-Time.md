# 743J Network Delay Time
https://leetcode.com/problems/network-delay-time/

## Method Best: Dijkstra 算法

这个题一度被标记为简单，因为就是Dijkstra.
当然我并不会Dijkstra，因为准备太匆忙就上马刷题了。
不过发现很多人也不会。可以这本应当是科班的标配，
可见大多数人都不想读书。


```java
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        //这个题是很经典的 Dijkstra 算法，是一个BFS。
        //应当学习Dijkstra
        //这个题的频率不高 但是像谷歌之类的就爱考。
        // Integer 用来存 id, 
        // List<int[]> 用来保存所有的从这一点出发去到到点 以及距离。
        // 因此int[]里有两个元素 [1] 是 distance，[0]是 node
        Map<Integer,List<int[]>> graph = new HashMap<>();
        // 把所有的edge都记录下来
        for(int[] edge : times){
            int id = edge[0];
            int[] data = new int[]{edge[1],edge[2]};
            if(!graph.containsKey(edge[0])){
                graph.put(id, new ArrayList<int[]>());
            }
            graph.get(id).add(data);
        }
        
        //创建一个最小堆，其实默认就是最小堆 
        // 如果改成最大堆，那么就是找最长路径的算法。
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>( (info1, info2) -> info1[0] - info2[0] );
        //堆里 [0]是distance，[1]是node id 初始化
        heap.offer(new int[]{0,K});
        
        // dist用来储存某个node离储存离选定节点的(最小)距离。
        // key是node, val是距离。
        // 如果dist不能取到所有的节点，说明有路不通。
        Map<Integer, Integer> dist = new HashMap<>();
        
        while(!heap.isEmpty()){ // 直到全部出列才停止。
            //取出目前距离最短的父节点和它到目标节点的距离
            int[] info = heap.poll();
            int distFather = info[0];
            int nodeFather = info[1];
            
            //如果已经在dist中记录过了 就跳过
            //这里跳过很重要，因为优先队列保证了，同一个节点，先进入的
            //一定是距离更短的那个组合。比如 3,A 一定比 4,A 先入列。
            if(dist.containsKey(nodeFather))  continue;
            
            //没有记录过 就把该点加进去，第一个是K,0 自己
            dist.put(nodeFather,distFather);
            
            //如果从这个父点出发有graph的路线
            if(graph.containsKey(nodeFather)){
            //那么就从这个node出发的所有路线都遍历
                for(int[] edge: graph.get(nodeFather)){
                    //目标node 和距离 命名为子节点和到父节点的距离
                    int nodeSon = edge[0];
                    int distSon  = edge[1];
                    //如果这个节点未被访问过，把它加上，距离当然是父子之和
                    if(!dist.containsKey(nodeSon)){
                        heap.offer(new int[]{distFather+distSon,nodeSon});
                    }
                }
            }
        }
        
        if(dist.size()!=N) return -1;
        int ans = 0;
        for(int cand : dist.values()){
            ans = Math.max(ans,cand);
        }
        return ans;
    }
}
```