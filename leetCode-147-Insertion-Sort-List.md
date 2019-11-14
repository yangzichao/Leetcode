# 147J. Insertion Sort List
https://leetcode.com/problems/insertion-sort-list/

插入排序 T O(N^2) S O(1)
## 方法 最佳
为了能够插入，指针必须要停在需要被插入位置的前一位;

```Java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode p = head;
        ListNode dummyhead = new ListNode(Integer.MIN_VALUE);
        ListNode p2 = dummyhead;

        while(p!=null){
           ListNode temp = dummyhead;  // temp 用来移动到需要被插入位置的前一位
            while(temp.next!=null && p.val>=temp.next.val){
                temp = temp.next; // temp.next!=null用来防止 temp.next.val 空指针
            }
            p2 = temp.next; // p2 用来固定 temp 后面的部分
            temp.next = p;
            p = p.next; // p 用来iterate over 原链表
            temp.next.next = p2;
        }

        return dummyhead.next;
    }
}
```
