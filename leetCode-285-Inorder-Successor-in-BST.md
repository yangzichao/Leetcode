# 285 Inorder-Successor-in-BST 
 
difficulty: Medium 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given a binary search tree and a node in it, find the in-order successor of that node in the BST.</p>
<p>The successor of a node&nbsp;<code>p</code>&nbsp;is the node with the smallest key greater than&nbsp;<code>p.val</code>.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_1.PNG" style="width: 122px; height: 117px;">
<pre><strong>Input: </strong>root = <span id="example-input-1-1">[2,1,3]</span>, p = <span id="example-input-1-2">1</span>
<strong>Output: </strong><span id="example-output-1">2</span>
<strong>Explanation: </strong>1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
</pre>
<p><strong>Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_2.PNG" style="width: 246px; height: 229px;">
<pre><strong>Input: </strong>root = <span id="example-input-2-1">[5,3,6,2,4,null,null,1]</span>, p = <span id="example-input-2-2">6</span>
<strong>Output: </strong><span id="example-output-2">null</span>
<strong>Explanation: </strong>There is no in-order successor of the current node, so the answer is <code>null</code>.
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ol>
	<li>If the given node has no in-order successor in the tree, return <code>null</code>.</li>
	<li>It's guaranteed that the values of the tree are unique.</li>
</ol>
</div></section>
 
 ## Method One 
 
 这个解法确实更好，因为利用了这个题目本身的BST的性质。

``` Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
​
        TreeNode candidate = null;
        TreeNode cur = root;
​
        while (cur != null) {
            if (cur.val > p.val) {
                candidate = cur;
                cur = cur.left;
            } else {
                // cur.val <= p.val
                cur = cur.right;
            }
        }
​
        return candidate;
    }
}
​
```

## Method 2 
直接利用 inorder  找到 successor 也很容易
```java
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right != null){
            p = p.right;
            while( p.left != null ) {
                p = p.left;
            }
            return p;
        }
        TreeNode prev = null;
        TreeNode cur = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while( !stack.isEmpty() || cur != null ) {
            while( cur != null ) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if( prev == p ) {
                return cur;
            }
            prev = cur;
            cur = cur.right;
        }
        return null;
    }
}
```