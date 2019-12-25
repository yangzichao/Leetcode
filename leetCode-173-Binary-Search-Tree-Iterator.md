# 173J. Binary Search Tree Iterator
https://leetcode.com/problems/binary-search-tree-iterator/

这个题跟[94](leetCode-094-Binary-Tree-Inorder-Traversal.md) 有关。


## Method Not Best
思路很简单，直接用Queue把元素in-order存下来
只是空间复杂度高了
```java
class BSTIterator {
    private Queue<TreeNode> queue;

    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        helper(root,queue);
    }
    public void helper(TreeNode root, Queue queue){
        if(root == null) return;
        helper(root.left,queue);
        queue.add(root);
        helper(root.right,queue);
    }
    /** @return the next smallest number */
    public int next() {
        return queue.remove().val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

```

## Method Best:
<pre>
这个方法真的值得大书特书。非常机智。
用一个栈保存所有的最左侧的子节点，有人说你这不是preorder吗？
但是由于栈是FILO/LIFO, 反倒是inorder了。
那么我们一个一个从栈里面往外取，一旦发现该节点的右侧不是空，
那么我们又直接取该节点右侧节点的所有的最左侧子节点。其实这个
过程就是模拟了inorder取节点的方式。只不过这样我们只需要维护
一个尺寸最多和树的高度一样的栈。

这个方法其实提供了一种新的看待inOrder Traversal的方法。值得学习
</pre>
```java
class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        _leftmostInorder(root);
    }
    private void _leftmostInorder(TreeNode root){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode topmostNode = stack.pop();
        if(topmostNode.right!=null){
            _leftmostInorder(topmostNode.right);
        }
        return topmostNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

```
