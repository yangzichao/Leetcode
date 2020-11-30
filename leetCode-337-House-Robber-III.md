# 337 House-Robber-III 
 
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
<div><p>The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.</p>
<p>Determine the maximum amount of money the thief can rob tonight without alerting the police.</p>
<p><b>Example 1:</b></p>
<pre><strong>Input: </strong>[3,2,3,null,3,null,1]
     <font color="red">3</font>
    / \
   2   3
    \   \ 
     <font color="red">3   1
</font>
<strong>Output:</strong> 7 
<strong>Explanation:</strong>&nbsp;Maximum amount of money the thief can rob = <font color="red" style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">3</font><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> + </span><font color="red" style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">3</font><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> + </span><font color="red" style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">1</font><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;"> = </span><b style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">7</b><span style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">.</span></pre>
<p><b>Example 2:</b></p>
<pre><strong>Input: </strong>[3,4,5,1,3,null,1]
&nbsp;    3
    / \
   <font color="red">4</font>   <font color="red">5</font>
  / \   \ 
 1   3   1
<strong>Output:</strong> 9
<strong>Explanation:</strong>&nbsp;Maximum amount of money the thief can rob = <font color="red">4</font> + <font color="red">5</font> = <b>9</b>.
</pre></div></section>
 
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
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max( res[0], res[1] );
    }
    public int[] dfs(TreeNode node) {
        int[] res = new int[2];
        if( node == null ) {
            return res;
        }
        
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        
        res[0] = left[1] + right[1] + node.val; // Don't rob left or right, rob this 
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // Doesn't have to rob children node.
        
        return res;
    }
}
​
/*
res[0] rob this one;
res[1] not rob this one;
*/
​
```