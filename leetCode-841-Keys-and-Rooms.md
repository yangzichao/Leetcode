# 841J. Keys and Rooms
https://leetcode.com/problems/keys-and-rooms/

## Method Best:
<pre>
其实这个题是一个最基础的图搜索的题。类似的有 1306. Jump Game III
用BFS和DFS都可以，区别就是用queue和stack。
都需要新建一个数组用来标记是否访问过。
</pre>

```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Stack<Integer> stack = new Stack();
        stack.push(0);
        
        while(!stack.isEmpty()){
            int cur = stack.pop();
            for(int key: rooms.get(cur)){
                if(!seen[key]){
                    seen[key] = true;
                    stack.push(key);
                }
            }
        }
        for(boolean s: seen){
            if(!s) return false;
        }
        return true;
        
    }
}
```
