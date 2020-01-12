# 199J. Binary Tree Right Side View
https://leetcode.com/problems/binary-tree-right-side-view/

## Method Best 秒杀
这个看起来就是假 level order traversal.
```java
class Solution {
    private List<Integer> ans;
    public List<Integer> rightSideView(TreeNode root) {
        ans = new ArrayList<>();
        scanner(root, 1);
        return ans;
    }
    
    private void scanner(TreeNode root, int level){
        if(root == null) return;
        if(ans.size() < level){
            ans.add(root.val);
        }
        scanner(root.right, level + 1);
        scanner(root.left, level + 1);
    }
}
```
