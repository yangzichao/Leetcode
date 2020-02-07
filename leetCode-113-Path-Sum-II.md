# 113J. Path Sum II

https://leetcode.com/problems/path-sum-ii/

我觉得这个题像 backtracking 啊

写了一个 PostOrder 的 其实比第一次写，写的还好。

```java
class Solution {
    private ArrayList<Integer> temp;
    private List<List<Integer>> ans;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.temp = new ArrayList<>();
        this.ans = new ArrayList<>();
        dfs(root,sum);
        return ans;
    }
    public void dfs(TreeNode root, int sum){
        if(root == null) return;
        int nSum = sum - root.val;
        temp.add(root.val);
        dfs(root.left, nSum);
        dfs(root.right, nSum);
        if(nSum == 0 && root.left == null && root.right == null){
            ans.add(new ArrayList<Integer>(temp));
        }
        temp.remove(temp.size() - 1);
    }
}
```
