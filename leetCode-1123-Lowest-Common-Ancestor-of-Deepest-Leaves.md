# 1123 Lowest-Common-Ancestor-of-Deepest-Leaves 
 
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
<div><p>Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.</p>
<p>Recall that:</p>
<ul>
	<li>The node of a binary tree is a <em>leaf</em> if and only if it has no children</li>
	<li>The <em>depth</em> of the root of the tree is 0, and if the depth of a node is <code>d</code>, the depth of each of its children&nbsp;is&nbsp;<code>d+1</code>.</li>
	<li>The <em>lowest common ancestor</em> of a set <code>S</code> of nodes is the node <code>A</code> with the largest depth such that every node in S is in the subtree with root <code>A</code>.</li>
</ul>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> root = [1,2,3]
<strong>Output:</strong> [1,2,3]
<strong>Explanation:</strong> 
The deepest leaves are the nodes with values 2 and 3.
The lowest common ancestor of these leaves is the node with value 1.
The answer returned is a TreeNode object (not an array) with serialization "[1,2,3]".
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> root = [1,2,3,4]
<strong>Output:</strong> [4]
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> root = [1,2,3,4,5]
<strong>Output:</strong> [2,4,5]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li>The given tree will have between 1 and 1000 nodes.</li>
	<li>Each node of the tree will have a distinct value between 1 and 1000.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int deepest = 0;
    TreeNode lca;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        findDeepest(root, 0);
        return lca;
    }
    public int findDeepest(TreeNode root, int depth ) {
        deepest = Math.max(deepest, depth);
        if(root == null) {
            return depth;
        }
        int left = findDeepest(root.left, depth + 1);
        int right = findDeepest(root.right, depth + 1);
        if( left == deepest && right == deepest ) {
            lca = root;
        }
        return Math.max(left, right);
    }
}
```