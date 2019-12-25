# 107J. Binary Tree Level Order Traversal II
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

这个题参见 [102](leetCode-102-Binary-Tree-Level-Order-Traversal.md)

## BFS
就是把加入的顺序换了
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
       LinkedList<List<Integer>> ans = new LinkedList<>();
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
            ans.addFirst(temp);
        }
        return ans;
    }
}
```

## DFS
同理 还是魔改102题
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if(root == null) return ans;
        helper(root, 0, ans);
        return ans;
    }
    public void helper(TreeNode node, int level, LinkedList<List<Integer>> ans){
        if(ans.size() == level){
            ans.addFirst(new ArrayList<Integer>());
        }
        if(node.left != null){
            helper(node.left, level + 1, ans);
        }
        if(node.right!=null){
            helper(node.right, level + 1, ans);
        }
        ans.get(ans.size()-level-1).add(node.val);
    } 
}
```