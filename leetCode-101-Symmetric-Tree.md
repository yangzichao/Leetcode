# 101J. Symmetric Tree
https://leetcode.com/problems/symmetric-tree/
## Method recursion:
```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }
    public boolean helper(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        
        
        return (t1.val == t2.val) &&helper(t1.left, t2.right) && helper(t1.right,t2.left);
    }
}
```
## Method Best:
<pre>
这个题就很有意思，
这个方法是同时做逆时针和顺时针 iterative BFS。


</pre>

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>(); 
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            q.offer(t1.left);
            q.offer(t2.right);
            q.offer(t1.right);
            q.offer(t2.left);
        }
        return true;
        
    }
}
```

可以想象 用stack做也是一样的。DFS

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>(); 
        stack.push(root);
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode t1 = stack.pop();
            TreeNode t2 = stack.pop();
            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            stack.push(t1.left);
            stack.push(t2.right);
            stack.push(t1.right);
            stack.push(t2.left);
        }
        return true;
        
    }
}

```