# 114J. Flatten Binary Tree to Linked List
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

## Method Recursion
要求 in place

这是我自己写的，还算比较直观
```java
class Solution {
    public void flatten(TreeNode root) {
        helper(root);
    }
    public TreeNode helper(TreeNode root){
        if(root == null) return null;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        TreeNode pL = left;
        if(left!= null){
            while(pL.right!=null){
                pL = pL.right;
            }
            root.left = null;
            root.right = left;
            pL.right = right;
        }
        return root;
    }
}
```
<pre>
这是从讨论中学到的，全局变量+顺时针的postorder。给跪了。
post order 对于一个 subtree 
比如 a.left = b, a.right = c 来说
处理的顺序是 b c a (逆时针) 或者 c b a （顺时针）
也就是说 ** post order 先处理子节点再处理父节点！**

而这题我们就要这样。c b a 的处理。
prev = c, 所以 b.right = prev; b.left = null;
然后令 prev = b; 下一轮处理 a 的时候就有 a.right = prev;
a.left = null; prev = root.

</pre>


```java
private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}
```

上面的解法不能解决全局变量能否重用的问题，做一个小的改变。
```java
class Solution {
    public void flatten(TreeNode root) {
        TreeNode prev = null;
        helper(root, prev);
    }
    public TreeNode helper(TreeNode root, TreeNode prev){
        if(root == null) return prev;
        prev = helper(root.right,prev);
        prev = helper(root.left,prev);
        root.right = prev;
        root.left = null;
        prev = root;
        return prev;
    }
}
```