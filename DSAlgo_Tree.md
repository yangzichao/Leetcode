# Tree 二叉树

## Tree Traversal

### Iterative

#### preorder

#### inorder

核心用 stack,先直接下到底。
逆时针
注意判断条件 (cur!=null || !stack.isEmpty())

```java
public void inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while(cur!=null || !stack.isEmpty()){
        while(cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        // 操作
        cur = cur.right;
    }
}
```

反向顺时针

```java
public void inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while(cur!=null || !stack.isEmpty()){
        while(cur!=null){
            stack.push(cur);
            cur = cur.right;
        }
        cur = stack.pop();
        // 操作
        cur = cur.left;
    }
}
```
