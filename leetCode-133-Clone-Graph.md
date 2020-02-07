# 133J. Clone Graph
https://leetcode.com/problems/clone-graph/

这个题和138有关
## Method DFS
关键的思想就在于，用HashMap
在原位就复制一个节点，并且将它固定住。

以下是一个分解步骤
```java
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }  
}
*/
class Solution {
    // HashMap 既可以建立新老节点的映射，也可以帮助判断图的遍历。
    private HashMap<Node,Node> oldToNew;
    
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        oldToNew = new HashMap<Node,Node>();
        // DFS 帮助在每个节点处复制一个节点
        dfs(node);
        // 对于每个节点，我们把它的邻居的关系都复制一遍。
        for(Node oldNode : oldToNew.keySet()){
            //这是这个节点对应的复制节点
            Node newNode = oldToNew.get(oldNode);
            //把它的邻居的复制节点都加到它的邻居列表里
            for(Node nei : oldNode.neighbors){
                newNode.neighbors.add(oldToNew.get(nei));
            }
        }
        return oldToNew.get(node);
    }
    private void dfs(Node node){
        //复制新node
        Node nNode = new Node(node.val);
        //标记原node
        oldToNew.put(node,nNode);
        for(Node n : node.neighbors){
            if(!oldToNew.containsKey(n)){
                dfs(n);
            }
        }
    }
}
```

精简版的代码可以这样

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }  
}
*/
class Solution {
    // HashMap 既可以建立新老节点的映射，也可以帮助判断图的遍历。
    private HashMap<Node,Node> oldToNew;
    
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        oldToNew = new HashMap<Node,Node>();
        return  dfs(node);
    }
    private Node dfs(Node node){
        // 边界条件，如果访问过，那么就把这个
        // 节点对应的克隆节点返回
        if(oldToNew.containsKey(node)){
            return oldToNew.get(node);
        }
        //复制新node
        Node nNode = new Node(node.val);
        //标记原node
        oldToNew.put(node,nNode);
        
        // 由于已经有了边界条件，所以直接把邻居都加一遍。
        for(Node n : node.neighbors){
            nNode.neighbors.add(dfs(n)) ;
        }
        return nNode;
    }
}
```