# 206J. Reverse Linked List
* what to do? think about recursion method.

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL  
Output: 5->4->3->2->1->NULL

Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both? No

## Method 1: Iteration Non-destructive

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
## Method 2 : Iteration destructive
用破坏的方法也可以
原理是运用双指针。
* 先排除空链表和只有一个元素的链表，直接返回。
* 然后用两个指针p p2固定住前两节，并且直接把第一节断开。
* p2 进行循环的时候，需要用一个temp ListNode把p2.next固定住，以防被垃圾回收找不到地址。
* 这样将指针排好序复位即可。

Iteration的方法只能够从前到后改变Node的指向，不能够回退。若想回退是需要用recursion的。


```Java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode p2 = head.next;
        p.next = null;
        while (p2!=null){
            ListNode temp = p2.next;
            p2.next = p;
            p = p2;
            p2 = temp;
        }
        return p;
    }
}
```
## Method 3: Recursion

This is able to be done in recursion for sure. But I don't know for now.
