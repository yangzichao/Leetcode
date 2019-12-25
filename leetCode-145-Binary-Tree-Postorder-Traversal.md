# 145J. Binary Tree Postorder Traversal
https://leetcode.com/problems/binary-tree-postorder-traversal/

## Trivial

no more words
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
        
    }
    public void inorder(TreeNode root, List list){
        if(root == null){
            return;
        }
        inorder(root.left, list);
        inorder(root.right, list);
        list.add(root.val);
    }
}
```
## iterative

见[上一题](leetCode-144-Binary-Tree-Preorder-Traversal.md)

注意这里 ans 专门定义成 LinkedList是因为 List接口本身并没有实现 addFirst()

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> ans = new LinkedList<>();
        if(root == null) return ans;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            ans.addFirst(node.val);
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        return ans;
    }
}
```