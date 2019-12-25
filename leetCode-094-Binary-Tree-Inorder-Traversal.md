# 094J. Binary Tree Inorder Traversal
https://leetcode.com/submissions/detail/280896270/


## Method Trivial:
Trivial
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
        
    }
    public void inorder(TreeNode root, List list){
        if(root == null){
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
```

## Method Iterative:

思想始终是，先一路左侧下到底，然后回退一格，然后取右子树，仍旧左侧下到底。
由于Stack是先入后出，因此间接的in order了。
这个代码是逆时针inorder， 改成顺时针的inorder只需要把 .right 和 .left 互换。
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;
    }
}
```