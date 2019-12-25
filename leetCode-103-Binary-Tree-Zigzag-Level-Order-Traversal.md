# 103J. Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/


这个题还是魔改自 102 题
## BFS
这个题和102差不多，但是由于情况不同，用了deque.
我们希望奇数层的情况和102题一模一样，所以调整了偶数层来适应奇数层。
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        int depth = 1;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            while(size > 0){

                if(depth%2 == 0){//偶数层
                    TreeNode node = q.removeLast();
                    temp.add(node.val);
                    if(node.right != null){
                        q.addFirst(node.right);
                    }                    
                    if(node.left != null){
                        q.addFirst(node.left);
                    }                      
                }else{ //奇数层
                    TreeNode node = q.removeFirst();
                    temp.add(node.val);                     
                    if(node.left != null){
                        q.addLast(node.left);
                    }  
                    if(node.right != null){
                        q.addLast(node.right);
                    }                    
                }

                size--;
            }
            ans.add(temp);
            depth++;
        }
        return ans;
    }
}
```