# 450 Delete-Node-in-a-BST

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
<div><p>Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.</p>
<p>Basically, the deletion can be divided into two stages:
</p><ol>
<li>Search for a node to remove.</li>
<li>If the node is found, delete the node.</li>
</ol>
<p></p>
<p><b>Note:</b> Time complexity should be O(height of tree).</p>
<p><b>Example:</b>
</p><pre>root = [5,3,6,2,4,null,7]
key = 3
    5
   / \
  3   6
 / \   \
2   4   7
Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
    5
   / \
  4   6
 /     \
2       7
Another valid answer is [5,2,6,null,4,null,7].
    5
   / \
  2   6
   \   \
    4   7
</pre>
<p></p></div></section>
 
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
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode prev = null;
        TreeNode cur = root;
        while(cur !=  null ) {
            if(cur.val == key) {
                break;
            }
            prev = cur;
            cur = key < cur.val ? cur.left: cur.right;
        }
        if(cur == null) {
            return root;
        }
        
        if(prev == null) {
            return deleteRootNode(root);
        }
        
        if(prev.left == cur) {
            prev.left = deleteRootNode(cur);
        }
        if(prev.right == cur) {
            prev.right = deleteRootNode(cur);
        }
        return root;
    }
    
    
    public TreeNode deleteRootNode (TreeNode node) {
        if(node == null) return null;
        if(node.left == null) return node.right;
        if(node.right == null) return node.left;
        
        TreeNode subRight = node.right;
        TreeNode newRoot = node.left;
        node = newRoot;
        while(node.right != null) {
            node = node.right;
        }
        node.right = subRight;
        return newRoot;
    }
}
​
```
