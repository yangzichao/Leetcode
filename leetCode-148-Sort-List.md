# 148J. Sort List
https://leetcode.com/problems/sort-list/

## Method Good!

这个方法就是 Merge Sort.
利用了 [876](leetCode-876-Middle-of-the-Linked-List.md) 找链表中点。
利用了 [021](leetCode-021-Merge-Two-Sorted-Lists.md) 合并有序链表。

最关键的是这一行
```java
 if(head == null || head.next == null) return head;
```
这一行的 head.next == null, 确保了分割的最小单元是一个节点，而不是null。
这样的话两个单独的节点自动就被mergesort了。从而全局有序。
缺少了这一行的代码是运行错误的。


```java
class Solution {
    public ListNode sortList(ListNode head) {
        // 这个 head.next == null 很重要，确保分割的最小单元是一个节点而不是 null
        if(head == null || head.next == null) return head;
        
        ListNode pre  = null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        pre.next = null;
        
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return mergeTwoLists(l1,l2);
        
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(l1!=null || l2 != null){
            if(l1 == null){
                p.next = l2;
                break;
            }
            if(l2 == null){
                p.next = l1;
                break;
            }
            if(l1.val < l2.val){
               p.next = l1;
               l1 = l1.next;
            }else{
               p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
            p.next = null;
        }
        return dummy.next;
    }    
}
```