# 096J. Unique Binary Search Trees
https://leetcode.com/problems/unique-binary-search-trees/


## Method 
<pre>
应当好好分析一下这个问题。
考虑
1, ..., i - 1, i, i + 1 ..., n
左侧的 i - 1 个节点组成的不重复BST, 假设有 ans[i - 1]个 ,
右侧的 n - i 个节点组成的不重复BST, 假设有 ans[n - i]个，
由于左侧的元素都比 i 小，右侧的元素都比 i 大，
从左侧的BST集合中任取一个作为左侧子树，
从右侧的BST集合中任取一个作为右侧子树，
不重复的组合显然是 ans[i - 1]*ans[n - i] 个；
假设之前有 n - 1 个节点，那么增加一个节点之后，一共有n个。
其不重复BST总数是令 i 从 1 取到 n 得到的所有 ans[i-1]*ans[n - 1] 之和。

即，设 ans[n-1] 已知，我们增加到 n 的话，总数就需要这么求：
i = 1, ans[n] += ans[0] * ans[n - 1];
i = 2, ans[n] += ans[2] * ans[n - 2];
...
i = i, ans[n] += ans[i - 1]*ans[n - i];
...
i = n, ans[n] += ans[n - 1] * ans[0];

因此初值条件有 
ans[0] = 1;
ans[1] = 1;

由于上面我们假设已知了 ans[n - 1], 实际上为了求
ans[n - 1], 我们还是得从 ans[2] 一个一个求上去。
因此为了求 ans[n],我们需要求并且保存下ans[2] 到 ans[n-1]
之间的所有的值。
代码如下
</pre>
```java
class Solution {
    public int numTrees(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1; // 没用
        ans[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                ans[i] += ans[j - 1] * ans[i - j];
            }
        }
        return ans[n];
    }
}
```