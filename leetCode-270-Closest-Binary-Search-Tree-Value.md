# 270 Closest-Binary-Search-Tree-Value

difficulty: Easy

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.</p>
<p><b>Note:</b></p>
<ul>
	<li>Given target value is a floating point.</li>
	<li>You are guaranteed to have only one unique value in the BST that is closest to the target.</li>
</ul>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> root = [4,2,5,1,3], target = 3.714286
    4
   / \
  2   5
 / \
1   3
<strong>Output:</strong> 4
</pre>
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
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;
​
        int closest = root.val; 
        while(cur != null) {
            closest =  Math.abs(target - cur.val) < Math.abs(target - closest) ?  cur.val : closest;
            if( target < cur.val ) {
                cur = cur.left;
            }else if( target > cur.val) {
                cur = cur.right;
            }else{
                return cur.val;
            }
        }
        return closest;
    }
}
​
```
