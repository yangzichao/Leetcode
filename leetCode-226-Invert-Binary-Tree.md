# 226J. Invert Binary Tree
https://leetcode.com/problems/invert-binary-tree/


## Method: Only and Best

```Java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.right = left;
        root.left = right;
        return root;
    }
}
```
