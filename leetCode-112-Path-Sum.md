# 112J. Path Sum

https://leetcode.com/problems/path-sum/

Path Sum 全家桶我觉得还是挺有意思的。

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false; // 边界条件而已
        int nSum = sum -= root.val;
        if(nSum == 0 && root.left == null && root.right == null){
            return true;
        }
        return hasPathSum(root.left, nSum) || hasPathSum(root.right,nSum);
    }
}
```
