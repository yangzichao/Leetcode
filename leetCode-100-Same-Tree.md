# 100J. Same Tree

https://leetcode.com/problems/same-tree/

## Method Best: O(N), O(log(N))
easy 秒杀

```Java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null){
            return q == null;
        }
        if( q == null){
            return p == null;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        boolean val = p.val == q.val;
        return left && right && val;
    }

}
```
