# 206J. Reverse Linked List
* what to do? think about recursion method.

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL  
Output: 5->4->3->2->1->NULL

Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both? No

## Method 1: Iteration

Class Learned:
* 可以用 ListNode p = null; 来新建空指针。
* 其实很简单，我们需要两个指针来指同一个链表。用一个复制对应的Node并链接另一个。  
再把另一个重新指回新List。

```Java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode pr = null;
        ListNode pr2 = pr;

        while(p != null) {
            pr = new ListNode(p.val);
            pr.next = pr2;
            pr2 = pr;
            p = p.next;
        }

        return pr;
    }
}
```

## Method 2: Recursion

This is able to be done in recursion for sure. But I don't know for now.
