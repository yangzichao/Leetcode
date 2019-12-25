# 106J. Construct Binary Tree from Inorder and Postorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

这个题就是继承自105题，postorder只需要从右往左分析就好了。
[105题](leetCode-105-Construct-Binary-Tree-from-Preorder-and-Inorder-Traversal.md)
## Method 

```java
class Solution {
    // Map of InOrder
    private HashMap<Integer, Integer> inMap = new HashMap<>(); 
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i],i);
        }
        return helper(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode helper(int[] postorder, int posA, int posB, int[] inorder,int inA, int inB){
        if(posA > posB || inA > inB) return null;
        TreeNode root = new TreeNode(postorder[posB]);
        int i = inMap.get(root.val);
        int inILeft = i - inA;
        int inIRight = inB - i;
        
        root.left = 
            helper(postorder,posB - 1 - inILeft - inIRight, posB - 1 - inIRight,inorder, inA , i - 1);
        root.right =
            helper(postorder,posA, posB - 1,inorder, i + 1, inB);
        return root;
    }
}
```
