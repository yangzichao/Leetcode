# 207J Course Schedule
https://leetcode.com/problems/course-schedule/

## Method: Topological Sort

这个题是一个典型的拓扑排序，纯拓扑排序，会就会，不会就GG。

```java
class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        ArrayList<Integer>[] G = new ArrayList[n]; //Graph
        int[] preCourses = new int[n];
        
        ArrayList<Integer> bfs = new ArrayList();
        
        // 以下是产生Graph本身
        for (int i = 0; i < n; ++i) {
            G[i] = new ArrayList<Integer>();
        }
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);  // G[i]是第i个课，它是它的所有数值的前置课程
            preCourses[e[0]]++; //     记录e[0] 的课所需的前置课程数量。
        }
        
        for (int i = 0; i < n; ++i){
            if (preCourses[i] == 0) { // 如果该课不需要前置课程，加到BFS里
                bfs.add(i); 
            }
        } 
        
        
        // 把所有不需要前置课程的课都拿出来iteration
        for (int i = 0; i < bfs.size(); ++i){
            // G[bfs.get(i)] 是 j 们的前置课程。
            for (int j: G[bfs.get(i)]){
                //每次把前置课程数目减少1
                preCourses[j] -= 1;
                //如果减少到0，那么就把它加入bfs中去。注意bfs的size 还会变大。
                if (preCourses[j] == 0){
                    bfs.add(j); 
                }                          
            }         
        }
        //这最后把所有的课都加进来。如果不能做到就说明有问题。
        return bfs.size() == n;
    }
}
```