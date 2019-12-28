# 110J. Balanced Binary Tree
https://leetcode.com/problems/balanced-binary-tree/

## Method 不咋地

这个比较好想
就是先写[104题](leetCode-104-Maximum-Depth-of-Binary-Tree.md) 获取深度。
除非 两个子树都balance并且高度差小于2 才返回true.
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        return isBalanced(root.left) && isBalanced(root.right) &&Math.abs( getHeight(root.left) - getHeight(root.right) ) < 2;
    }
    
    public int getHeight(TreeNode root){
        if(root == null) return 0;
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        return Math.max(leftH, rightH) + 1;
    }
}
```
## Method Best
上面重复的调用了树本身。明显有冗余的部分。
一个繁琐的改进是改变Tree的节点定义。
如果能直接在获得高度的过程中就优化就好了。
一个很机智的改进是，如果发现子树就不平衡，就给他的高度改成 -1. 标记它是不平衡。这样就One Pass了。
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    
    public int getHeight(TreeNode root){
        if(root == null) return 0;
        int leftH = getHeight(root.left);
        int rightH = getHeight(root.right);
        
        if(leftH == -1 || rightH == -1 || Math.abs(leftH - rightH) > 1){
            return -1;
        }
        return Math.max(leftH, rightH) + 1;
    }
}
```
