# 1382 Balance-a-Binary-Search-Tree 
 
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
<div><p>Given a binary search tree, return a <strong>balanced</strong> binary search tree with the same node values.</p>
<p>A binary search tree is <em>balanced</em> if and only if&nbsp;the depth of the two subtrees of&nbsp;every&nbsp;node never differ by more than 1.</p>
<p>If there is more than one answer, return any of them.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/08/22/1515_ex1.png" style="width: 250px; height: 248px;"><img alt="" src="https://assets.leetcode.com/uploads/2019/08/22/1515_ex1_out.png" style="width: 200px; height: 200px;"></strong></p>
<pre><strong>Input:</strong> root = [1,null,2,null,3,null,4,null,null]
<strong>Output:</strong> [2,1,3,null,null,null,4]
<b>Explanation:</b> This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li>The number of nodes in the tree is between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>10^4</code>.</li>
	<li>The tree nodes will have distinct values between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>10^5</code>.</li>
</ul></div></section>
 
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
    private List<TreeNode> inorder;
    public TreeNode balanceBST(TreeNode root) {
        this.inorder = new ArrayList<>();
        inorderTraversal(root);
        return balanceBST(0, inorder.size() - 1);
    }
    public void inorderTraversal(TreeNode node){
        if(node == null){
            return;
        }
        inorderTraversal(node.left);
        inorder.add(node);
        inorderTraversal(node.right);
    }
    public TreeNode balanceBST(int start, int end){
        if( start > end ){
            return null;
        }
        int pivot = start + (end - start)/2;
        TreeNode node = inorder.get(pivot);
        node.left = balanceBST(start,pivot -1 );
        node.right = balanceBST(pivot + 1, end);
        return node;
    }
}
​
```