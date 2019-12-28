# 109J. Convert Sorted List to Binary Search Tree
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/


## Method 1
[复制108](leetCode-108-Convert-Sorted-Array-to-Binary-Search-Tree.md)
先把LinkedList都加到数组里，再做108一样的。
## Method 2 模拟 inorder
真的好机智
先取这个数组的size，这个还是不能避免的。

```java
class Solution {
    private ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
        int size = getsize(head);
        this.head = head;
        return convertListToBST(0,size -1);
    }
    
    public int getsize(ListNode head){
        ListNode p = head;
        int size  = 0;
        while(p!=null){
            p = p.next;
            size++;
        }
        return size;
    }
    public TreeNode convertListToBST(int l, int r){
        if(l>r) return null;
        int mid = l + (r - l)/2;
        TreeNode left = convertListToBST(l, mid-1);
        TreeNode node = new TreeNode(this.head.val);
        this.head = this.head.next;
        
        node.left  = left;
        node.right = convertListToBST(mid + 1,r);
        return node;
    }
}
```