# 098J. Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree/

## Method 1 
<pre>
先用栈inorder记录下所有元素，然后用出栈看是否有序
</pre>

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        Stack<Integer> s = new Stack<>();
        inorder(root, s);
        int temp = s.pop();
        while(!s.isEmpty()){
            if(temp <= s.peek()){
                return false;
            }
            temp = s.pop();
        }
        return true;
    }
    public void inorder(TreeNode root, Stack s){
        if(root == null){
            return;
        }
        inorder(root.left, s);
        s.push(root.val);
        inorder(root.right, s);
    }
}
```

## Method 2: Iteration
in order simulation

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int curval = 0;
        double lastval = -Double.MAX_VALUE;
        while( cur!=null || !stack.isEmpty()){            
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            curval = cur.val;
            if(curval <= lastval) return false;
            lastval = curval;
            cur = cur.right;
        }
        return true;
    }
}
```
