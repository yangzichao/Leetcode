# 314 Binary-Tree-Vertical-Order-Traversal

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
<div><p>Given a binary tree, return the <i>vertical order</i> traversal of its nodes' values. (ie, from top to bottom, column by column).</p>
<p>If two nodes are in the same row and column, the order should be from <b>left to right</b>.</p>
<p><b>Examples 1:</b></p>
<pre><strong>Input:</strong> <code>[3,9,20,null,null,15,7]
</code>
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 
<strong>Output:</strong>
[
  [9],
  [3,15],
  [20],
  [7]
]
</pre>
<p><b>Examples 2:</b></p>
<pre><strong>Input: </strong><code>[3,9,8,4,0,1,7]
</code>     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 
<strong>Output:</strong>
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
</pre>
<p><b>Examples 3:</b></p>
<pre><strong>Input:</strong> <code>[3,9,8,4,0,1,7,null,null,null,2,5]</code> (0's right child is 2 and 1's left child is 5)
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
<strong>Output:</strong>
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
</pre></div></section>
 
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 因为要求 left to right. 所以必须 BFS了。
        // 核心是以root为0行0列，其左右node 是 (-1,1) and (1,1).
        // 核心2是用一个额外的 queue 同步记录 列编号
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        //这个是为了不在最后额外对col坐标即横坐标排序。
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if(root == null) {
            return ans;
        }
        
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> colQ   = new LinkedList<>();
        
        nodeQ.offer(root);
        colQ.offer(0);
        while(!nodeQ.isEmpty()) {
            int size = nodeQ.size();
            while( size > 0) {
                TreeNode cur = nodeQ.poll();
                int col      = colQ.poll();
                min = Math.min(col, min);
                max = Math.max(col, max);
                
                map.putIfAbsent(col, new ArrayList<Integer>());
                map.get(col).add(cur.val);
                if(cur.left != null){
                    nodeQ.offer(cur.left);
                    colQ.offer(col - 1);
                }
                if(cur.right != null) {
                    nodeQ.offer(cur.right);
                    colQ.offer(col + 1);
                }
                size--;
            }
        }
        for(int i = min; i <= max; i++) {
            ans.add(map.get(i));
        }
        return ans;
    }
}
​
```
