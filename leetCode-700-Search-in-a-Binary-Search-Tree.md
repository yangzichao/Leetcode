# 700J. Search in a Binary Search Tree

这是基本操作，详情见算法binary Tree

## Best

```Java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if(root.val > val){
            return searchBST(root.left, val);
        }else if(root.val < val){
            return searchBST(root.right, val);
        }else{
            return root;
        }
    }
}
```
