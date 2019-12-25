# 105J. Construct Binary Tree from Preorder and Inorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

## Method 1: 朴素
搞明白 preorder的第一个是root, 
然后 inorder数组中找到root的位置， 那么
root 左侧的子数组，都在root的左侧的子树，而root右侧的子数组都在root右侧的子树。


```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) return null;
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        
        int i = 0;
        for(i = 0; i < inorder.length; i++){
            if(inorder[i] == rootVal){
                break;
            }
        }  
  
        root.left = buildTree(Arrays.copyOfRange(preorder,1,1+i),
                          Arrays.copyOfRange(inorder,0,i));
        root.right = buildTree(Arrays.copyOfRange(preorder,1+i,preorder.length),
                           Arrays.copyOfRange(inorder,i+1,inorder.length));     
        return root;
    }
}
```

## 提升
方法是用 HashMap 缓存住，增加查找的速度
```java
class Solution {
    // Map of InOrder
    private HashMap<Integer, Integer> inMap = new HashMap<>(); 
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i],i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode helper(int[] preorder, int preA, int preB, int[] inorder,int inA, int inB){
        if(preA > preB || inA > inB) return null;
        TreeNode root = new TreeNode(preorder[preA]);
        int i = inMap.get(root.val);
        int inILeft = i - inA;
        int inIRight = inB - i;
        
        root.left = 
            helper(preorder,preA + 1, preA + inILeft,inorder, inA , i - 1);
        root.right =
            helper(preorder,preA + 1 + inILeft, preA + 1 + inILeft + inIRight,inorder, i + 1, inB);
        return root;
    }
}
```

以上的代码中，inIleft + inIRight = inB - inA , 而 inB 实际上一直等于 inorder.length - 1, 可见有两个冗余的参数。





