# 617J. Merge Two Binary Trees
https://leetcode.com/problems/merge-two-binary-trees/

## Method Easy:

<pre>
递归的方法很简单，但是不是空间最优。但是仍然双百
</pre>

```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
```

## Method Best:
<pre>

</pre>

```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null){
            return t2;
        }
        Stack<TreeNode[] > stack = new Stack<>();
        stack.push(new TreeNode[]{t1,t2});
        while(!stack.isEmpty()){
            TreeNode[] t = stack.pop();
            // 如果遇到右侧不存在的空节点，直接跳过，去处理栈中剩下的部分。
            if( t[1] == null) {
                continue;
            }
            //
            t[0].val += t[1].val;
            //先push右侧，后处理它，这是在模拟递归的过程。
            if(t[0].right == null){
                t[0].right = t[1].right;
            }else{
                stack.push(new TreeNode[]{t[0].right, t[1].right});
            }
            
            if(t[0].left == null){
                t[0].left = t[1].left;
            }else{
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }

        }
        return t1;
    }
}
```
