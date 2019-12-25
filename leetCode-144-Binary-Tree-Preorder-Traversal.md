# 144J. Binary Tree Preorder Traversal
https://leetcode.com/problems/binary-tree-preorder-traversal/

preorder 和 postorder实际上有一个关连。
逆时针的preorder 取得的顺序和 顺时针的postorder 是完全相反的。
同理
逆时针的postorder和顺时针的preorder取的顺序也是完全互为逆序。
## Method Trivial:
No more words
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }
    
    public void helper(TreeNode root, List<Integer> ans){
        if(root == null) return;
        ans.add(root.val);
        helper(root.left, ans);
        helper(root.right, ans);
    }
}
```

## Method Iterative:
这个方法解释了 Stack 和 recursion 的关连。
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            ans.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }   
        }
        return ans;        
    }
}
```
