# 104J. Maximum Depth of Binary Tree
https://leetcode.com/problems/maximum-depth-of-binary-tree/
<pre>
这个题本身非常简单，
但是非常适合进入新手村的我建立stack与recursion的联系，
以及DFS，BFS。
这个讨论非常有意义
https://leetcode.com/problems/maximum-depth-of-binary-tree/discuss/34195/Two-Java-Iterative-solution-DFS-and-BFS

</pre>

## Method: recursion
这个方法不多说了
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }
}
```
## Method: DFS
<pre>
仔细想想
递归的方式本质上就是一个Stack的过程，同时也是一个DFS。

我们要做的是想办法重写以上代码，令其变为一个Stack的过程。
思考 我们在上面recursion的过程中，每一层实际上记录了不仅仅
有root本身，还有高度也在每一层当中暂存。因此我们需要两个
stack，一个存node，一个存对应的depths。

recursion的边界条件在这里就不存在了，变为了结束条件while(!stack.isEmpty())。

</pre>
```java
class Solution {
    public int maxDepth(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        if(root == null) return 0;
        stack.push(root);
        depths.push(1);
        int ans = 1;
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            int depth = depths.pop();
            ans = Math.max(ans,depth);
            if(node.left!=null){
                stack.push(node.left);
                depths.push(depth+1);
            }
            if(node.right!=null){
                stack.push(node.right);
                depths.push(depth+1);              
            }
        }
        return ans;
    }
}
```
## Method: BFS

<pre>
用Stack实现了深度优先搜索，那么可以用Queue实现BFS,
只需要对代码稍微改一改。事实上这个题反而更适合BFS。
因为可以少维护一个stack. 事实上这个也的确比DFS更快
</pre>

```Java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root ==null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0; // 这里是因为第一次循环就加1了
        while(!q.isEmpty()){
            int size = q.size();//记录的是这一层有多少个元素
            while(size > 0){ //收录这一层所有元素的children
                TreeNode node = q.poll();
                if(node.left != null ){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }
}
```
