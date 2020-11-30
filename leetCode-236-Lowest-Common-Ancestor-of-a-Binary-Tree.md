# 236 Lowest-Common-Ancestor-of-a-Binary-Tree

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
<div><p>Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.</p>
<p>According to the <a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor" target="_blank">definition of LCA on Wikipedia</a>: “The lowest common ancestor is defined between two nodes p&nbsp;and q&nbsp;as the lowest node in T that has both p&nbsp;and q&nbsp;as descendants (where we allow <b>a node to be a descendant of itself</b>).”</p>
<p>Given the following binary tree:&nbsp; root =&nbsp;[3,5,1,6,2,0,8,null,null,7,4]</p>
<img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height: 190px;">
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
<strong>Output:</strong> 3
<strong>Explanation: </strong>The LCA of nodes <code>5</code> and <code>1</code> is <code>3.</code>
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
<strong>Output:</strong> 5
<strong>Explanation: </strong>The LCA of nodes <code>5</code> and <code>4</code> is <code>5</code>, since a node can be a descendant of itself according to the LCA definition.
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ul>
	<li>All of the nodes' values will be unique.</li>
	<li>p and q are different and both values will&nbsp;exist in the binary tree.</li>
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 观察发现, 最近的公共祖先有什么特征呢？
    // 当 p 和 q 在自己的左右子树各一个的时候，它是一个公共祖先。
    // 还有一种情况就是，它自己是 p 和 q 之一。而且它的左右子树有一个包含另一个。
    private TreeNode ancestor;
    private TreeNode p;
    private TreeNode q;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.ancestor = root;
        this.p = p;
        this.q = q;
        dfs(root);
        return ancestor;
    }
    
    public boolean dfs(TreeNode node) {
        if(node == null) {
            return false;
        }
        
        boolean isAtLeft = dfs(node.left);
        boolean isAtRight = dfs(node.right);
        
        if( (isAtLeft || isAtRight) && (node == this.p || node == this.q ) ) {
            this.ancestor = node;
            return true;
        }
        
        if( isAtLeft && isAtRight) {
            this.ancestor = node;
            return true;           
        }
        
        if( isAtLeft || isAtRight || node == this.p || node == this.q ) {
            return true;
        }
        return false;
    }
}
​
```


```java
class Solution {
    // 这个解法特别好。
    // 我们在左边找，找不到的话说明在右边。
    // 我么在右边找，找不到的话说明在左边。
    // 实际上我们做的是什么？我们做的是 postOrder 搜索，找的 target 是 p 或者 q.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q ) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if( left == null ) {
            return right;
        }
        if( right == null ) {
            return left;
        }
        return root;
    }
}
```