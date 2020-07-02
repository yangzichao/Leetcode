# 1311J. Get Watched Videos by Your Friends

https://leetcode.com/problems/get-watched-videos-by-your-friends/

## Method Ordinary

分析这个题就三步，  
第一步建图  
第二步 BFS 找出该层所有朋友  
第三部 过滤

```java
class Solution {
    private Map<String, Integer> map;
    private boolean[] visited;
    private List<String> ans;
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        this.visited = new boolean[friends.length];
        List<Integer>[] adj = new ArrayList[friends.length];
        for(int i = 0; i < friends.length; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i < friends.length; i++){
            for(int f : friends[i]){
                adj[i].add(f);
            }
        }


        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(id);
        while( level > 0){
            int size = q.size();
            while(size > 0){
                int v = q.poll();
                visited[v] = true;
                for(int n : adj[v]){
                    if(!visited[n]){
                        q.offer(n);
                    }
                }
                size--;
            }
            level--;
        }

        map = new HashMap<>();

        while(!q.isEmpty()){
            int tempQ = q.poll();
            if(! visited[tempQ] ){
                for(String video : watchedVideos.get(tempQ)){
                    map.put(video, map.getOrDefault(video,0) + 1);
                }
                visited[tempQ] = true;
            }
        }

        ans = new LinkedList<>(map.keySet());

        ans.sort((a,b) -> {
            if(map.get(a) != map.get(b)){
                return map.get(a) - map.get(b);
            }
            return a.compareTo(b);
        });

        ans.sort(new pseudoComparator());
        return ans;
    }

    // 这个代码是用来手动比较器的
    private class pseudoComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            if(map.get(s1) != map.get(s2)){
                return map.get(s1) - map.get(s2);
            }
            return s1.compareTo(s2);
        }
    }

}
```
