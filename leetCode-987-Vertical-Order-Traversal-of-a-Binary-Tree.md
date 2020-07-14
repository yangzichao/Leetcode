# 987 Vertical-Order-Traversal-of-a-Binary-Tree

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
<div><p>Given a binary tree, return the <em>vertical order</em> traversal of its nodes&nbsp;values.</p>
<p>For each node at position <code>(X, Y)</code>, its left and right children respectively&nbsp;will be at positions <code>(X-1, Y-1)</code> and <code>(X+1, Y-1)</code>.</p>
<p>Running a vertical line from <code>X = -infinity</code> to <code>X = +infinity</code>, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing <code>Y</code> coordinates).</p>
<p>If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.</p>
<p>Return an list&nbsp;of non-empty reports in order of <code>X</code> coordinate.&nbsp; Every report will have a list of values of nodes.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<p><img alt="" src="https://assets.leetcode.com/uploads/2019/01/31/1236_example_1.PNG" style="width: 201px; height: 186px;"></p>
<div>
<pre><strong>Input: </strong><span id="example-input-1-1">[3,9,20,null,null,15,7]</span>
<strong>Output: </strong><span id="example-output-1">[[9],[3,15],[20],[7]]</span>
<strong>Explanation: </strong>
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
</pre>
<div>
<p><strong>Example 2:</strong></p>
<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/01/31/tree2.png" style="width: 236px; height: 150px;"></strong></p>
<pre><strong>Input: </strong><span id="example-input-2-1">[1,2,3,4,5,6,7]</span>
<strong>Output: </strong><span id="example-output-2">[[4],[2],[1,5,6],[3],[7]]</span>
<strong>Explanation: </strong>
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
</pre>
<p>&nbsp;</p>
</div>
<p><strong>Note:</strong></p>
<ol>
	<li>The tree will have between <font face="monospace">1</font>&nbsp;and <code>1000</code> nodes.</li>
	<li>Each node's value will be between <code>0</code> and <code>1000</code>.</li>
</ol>
</div>
<div>
<div>&nbsp;</div>
</div>
</div></section>
 
 ## Method One 
 
``` Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * } 
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //和314不一样的地方是，要新建一个 tempMap,然后扫里面全部的list.
        // BFS, easy to think about the order.
        // use an extra queue to record the x-coordinates.
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        
        Map<Integer, List<Integer>> ansMap = new HashMap<>();
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<Integer> qCol   = new LinkedList<>();
        
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        
        qNode.offer(root);
        qCol.offer(0);
        
        while(!qNode.isEmpty()) {
            int qSize = qNode.size();
            Map<Integer, List<Integer>> tempMap = new HashMap<>();
            while(qSize > 0) {
                TreeNode cur = qNode.poll();
                int col      = qCol.poll();
                
                tempMap.putIfAbsent(col, new ArrayList<Integer>());
                tempMap.get(col).add(cur.val);
                    
                if(minCol > col) {
                    minCol = col;
                }
                if(maxCol < col) {
                    maxCol = col;
                }
                
                if(cur.left != null) {
                    qNode.offer(cur.left);
                    qCol.offer(col - 1);
                }
                if(cur.right != null) {
                    qNode.offer(cur.right);
                    qCol.offer(col + 1);
                }
                qSize--;
            }
            for(int col : tempMap.keySet()) {
                ansMap.putIfAbsent(col, new ArrayList<Integer>());
                List<Integer> list = tempMap.get(col);
                Collections.sort(list);
                ansMap.get(col).addAll(list);
            }
        }
        
        for(int i = minCol; i <= maxCol; i++ ) {
            ans.add(ansMap.get(i));
        }
        return ans;
    }
}
```
