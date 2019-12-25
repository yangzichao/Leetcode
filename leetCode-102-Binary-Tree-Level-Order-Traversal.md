# 102J. Binary Tree Level Order Traversal
https://leetcode.com/problems/binary-tree-level-order-traversal/

## Method Iteration BFS
<pre>
这个就是一个简单的BFS，所以用Queue就能比较轻松的写出来。
</pre>
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            while(size > 0){
                TreeNode node = q.poll();
                temp.add(node.val);
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
                size--;
            }
            ans.add(temp);
        }
        return ans;
    }
}
```


## Method Recursion DFS
<pre>
BFS 也是可以用recursion写， 就是有点啰嗦。
我们的helper函数，要伸进去一个全局的ans，而且要用一个level来表示到了树的第几层。
加入我们来到了第i层(i从0开始），而这一层目前还从未被访问过，
那么长度目前为i的ans就要新建一个list来储存这一层的数字。反之就直接把数字储存在这一层的list里。
然后让左右节点深入到下一层去。

这种方法原则上不是BFS，仍旧是DFS，但是达到了BFS的效果。
</pre>
举个例子
![example](imgs/LC102.jpg)
这其实相当于preorder遍历，但是把东西都存在该存的层里。
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        helper(root, 0, ans);
        return ans;
    }
    public void helper(TreeNode node, int level, List<List<Integer>> ans){
        if(ans.size() == level){
            ans.add(new ArrayList<Integer>());
        }
        
        ans.get(level).add(node.val);
        
        if(node.left != null){
            helper(node.left, level + 1, ans);
        }
        if(node.right!=null){
            helper(node.right, level + 1, ans);
        }
    } 
}
```