# 235J. Lowest Common Ancestor of a Binary Search Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

## Method 1 Recursion
这是个easy题没啥好说的

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;

        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }else if(p.val == root.val || q.val == root.val){
            return root;
        }else{
            return root;
        }
    }
}
```
明显后面两种可以合并，简化代码
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;

        if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
}
```

## Method 2 Iterative

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        TreeNode node = root;

        while(p!=null){
            if(p.val < node.val && q.val < node.val){
                node = node.left;
            }else if( p.val > node.val && q.val > node.val ){
                node = node.right;
            }else{
                return node;
            }
        }
        return node;
    }
}
```

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while( true ) {
            if( p.val < node.val && q.val < node.val ) {
                node = node.left;
            }else if( p.val > node.val && q.val > node.val ) {
                node = node.right;
            }else{
                return node;
            }
        }
        // return node;
    }
}
```