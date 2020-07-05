# 653 Two-Sum-IV---Input-is-a-BST

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
<div><p>Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> 
    5
   / \
  3   6
 / \   \
2   4   7
Target = 9
<b>Output:</b> True
</pre>
<p>&nbsp;</p>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> 
    5
   / \
  3   6
 / \   \
2   4   7
Target = 28
<b>Output:</b> False
</pre>
<p>&nbsp;</p>
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
    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        
        stack.push(root);
        while( !stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if(cur.right !=null) {
                stack.push(cur.right);
            }
            
            if(cur.left != null ) {
                stack.push(cur.left);
            }
            if(set.contains(k - cur.val) ){
                return true;
            }
            set.add(cur.val);
        }
        return false;
    }
}
​
```
