# 662 Maximum-Width-of-Binary-Tree 
 
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
<div><p>Given a binary tree, write a function to get the maximum width of the given tree. The maximum width of a tree is the maximum width among all levels.</p>
<p>The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the <code>null</code> nodes between the end-nodes are also counted into the length calculation.</p>
<p>It is <strong>guaranteed</strong> that the answer will in the range of 32-bit signed integer.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> 
           1
         /   \
        3     2
       / \     \  
      5   3     9 
<b>Output:</b> 4
<b>Explanation:</b> The maximum width existing in the third level with the length 4 (5,3,null,9).
</pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> 
          1
         /  
        3    
       / \       
      5   3     
<b>Output:</b> 2
<b>Explanation:</b> The maximum width existing in the third level with the length 2 (5,3).
</pre>
<p><b>Example 3:</b></p>
<pre><b>Input:</b> 
          1
         / \
        3   2 
       /        
      5      
<b>Output:</b> 2
<b>Explanation:</b> The maximum width existing in the second level with the length 2 (3,2).
</pre>
<p><b>Example 4:</b></p>
<pre><b>Input:</b> 
          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
<b>Output:</b> 8
<b>Explanation:</b>The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li>The&nbsp;given binary tree will have between&nbsp;<code>1</code>&nbsp;and&nbsp;<code>3000</code>&nbsp;nodes.</li>
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
    private Map<Integer, Integer> leftMostIndices;
    private int maxWidth;
    public int widthOfBinaryTree(TreeNode root) {
        this.leftMostIndices = new HashMap<>();
        this.maxWidth = 0;
        dfs(root, 0, 0);
        return maxWidth;    
    }
    public void dfs(TreeNode node, int level, int index) {
        if(node == null) {
            return;
        }
        leftMostIndices.putIfAbsent(level, index);
        int curWidth = index - leftMostIndices.get(level) + 1;
        maxWidth = Math.max(maxWidth, curWidth);
        dfs(node.left, level + 1, index * 2 );
        dfs(node.right, level + 1, index * 2 + 1);
    }
}
​
```