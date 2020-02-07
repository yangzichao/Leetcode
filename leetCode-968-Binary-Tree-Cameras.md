# 968J. Binary Tree Cameras

https://leetcode.com/problems/binary-tree-cameras/

这个题很好！

这个几乎想到正确的方法了。
事实上这个题是一个图的题而不是树的题。当然都差不多的。
一个观察就是，从底向上安摄像机是更优秀的。比如一个最底的节点，在
它身上安摄像机，当然不如安在它的父节点上。
那么就要 DFS 进入到最深层先。
为了从保证始终从最下层开始处理，要用 postorder.
用 inorder 为什么不行呢？可以考虑树是一个链表的情况。

直接看代码吧。

```java
class Solution {
    private Set<TreeNode> visited;
    private int ans;
    public int minCameraCover(TreeNode root) {
        this.ans = 0;
        this.visited = new HashSet<>();
        visited.add(null);
        dfs(root, null);
        return ans;
    }
    private void dfs(TreeNode node, TreeNode par){
        if(node == null) return;
        dfs(node.left, node);
        dfs(node.right, node);
        //一个节点的除非自己是root，否则不判断它自己。因为Set中有null,所以所有的叶节点都被排除了。
        //层层往上。其实代码还是能看懂的，也不难。这个题很好。
        if(par==null && !visited.contains(node) || !visited.contains(node.left) || !visited.contains(node.right) ){
            ans++;
            visited.add(node);
            visited.add(par);
            visited.add(node.left);
            visited.add(node.right);
        }
    }
}
```
