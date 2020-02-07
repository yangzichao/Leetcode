# 230J Kth Smallest Element in a BST

https://leetcode.com/problems/kth-smallest-element-in-a-bst/

## Method Recursion

秒杀

```java
class Solution {
    private int count;
    private int kth;
    public int kthSmallest(TreeNode root, int k) {
        this.count = k;
        this.kth = 0;
        inorder(root);
        return kth;
    }
    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        count--;
        if(count == 0) {
            kth = root.val;
            return;
        }
        inorder(root.right);
    }
}
```

## Method Iteration

没有意义 还是秒杀

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(true){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0){
                return root.val;
            }
            root = root.right;
        }
    }
}
```
