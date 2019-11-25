# 897J. Increasing Order Search Tree
https://leetcode.com/problems/increasing-order-search-tree/



## Method 1: 并非最佳 O(N) O(N)
适用于我这种菜鸡新手，刚刚做tree的题的那种。
首先inorder 还是会的，然后做一个helper function 用inorder的顺序把数字
都记录下来。然后再生成新的Tree。
```Java
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        inorder(root,vals);
        TreeNode ans = new TreeNode(-1), cur = ans;
        for(int v : vals){
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;

    }
    public void inorder(TreeNode root, List vals){
        if(root == null){
            return;
        }
        inorder(root.left,vals);
        vals.add(root.val);
        inorder(root.right,vals);

    }
}
```

## Method 2: Best O(N), O(H), H是Tree的高度

实际上比想像的还要简单一点。其原理其实是：从外部伸入一个树头部。
然后 inorder 操作一番。
缺点是：必须要有一个类变量作为指针。
```Java
class Solution {
    TreeNode p;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyHead = new TreeNode(-1);
        p = dummyHead;
        helper(root);
        return dummyHead.right;
    }

    public void helper(TreeNode root){
        if(root == null){
            return;
        }
        helper(root.left);
        p.right = root;  
        p = p.right;
        root.left = null;
        helper(root.right);
    }
}
```
