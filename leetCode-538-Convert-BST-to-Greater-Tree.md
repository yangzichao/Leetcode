# 538J. Convert BST to Greater Tree

https://leetcode.com/problems/convert-bst-to-greater-tree/

## Method Recursion

```java
class Solution {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {

        if(root != null){
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }

        return root;
    }
}
```
