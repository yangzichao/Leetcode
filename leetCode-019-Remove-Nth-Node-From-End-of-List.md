# 019J. Remove Nth Node From End of List

Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?


## Method 1:
Class Learned:
* Linked List 问题 无论如何，先新建一个dummy head.
* 返回原链表head，用返回dummyhead.next. 因为 head 本身指的位置是可能被破坏掉的。
* 其余的内容很简单，首先循环到底得到链表长度。然后循环到需要被删去的链表的上一节，跳过该节即可。
### Java Solution

```Java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int counter = 0;

        while (p!= null) {
            p = p.next;
            counter += 1;
        }
        counter -= n;

        ListNode p2 = new ListNode(0);
        p2.next = head;
        p = p2;
        while(counter > 0){
           counter -= 1;
            p = p.next;
        }
        // p = new ListNode(p2.next.val);
        p.next = p.next.next;
        return p2.next;     
    }
}
```
