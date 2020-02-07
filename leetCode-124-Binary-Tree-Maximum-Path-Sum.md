# 124J. Binary Tree Maximum Path Sum

https://leetcode.com/problems/binary-tree-maximum-path-sum/

其实树的题没有难题，嘿嘿嘿。

```java
class Solution {
    private int curMax;
    public int maxPathSum(TreeNode root) {
        this.curMax = Integer.MIN_VALUE;
        dfs(root);
        return curMax;

    }
    private int dfs(TreeNode root){
        //关键的观察是，一个树当中的道路，只能至多是两条从同一节点拼成的路
        //不能拐弯抹角的走
        if(root == null) return 0;
        int left = Math.max( dfs(root.left), 0 );
        int right = Math.max( dfs(root.right), 0);
        int ss = left + right + root.val;
        curMax = Math.max(ss, curMax);
        return root.val + Math.max(left,right);
    }

}
```
