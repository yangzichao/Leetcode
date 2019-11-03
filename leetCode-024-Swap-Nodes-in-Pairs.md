# 024J?. Swap Nodes in Pairs

* What else? Recursion.


Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.



Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.

## Method 1: Iteration.
这个题很简单。

Class Learned:
* 先建立 dummyhead 很重要。
* Iteration 方法 Swap 需要双指针。两个指针相邻。先将两个指针独立跳位，然后连起来两个指针即可。
* 注意 **while 需要指针连续往下两位都不为空**。第一次提交就在这里错了。

### Java Solution
```java
class Solution {
    public ListNode swapPairs(ListNode head) {

        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode p = dummyhead;
        while(p.next != null && p.next.next != null) {
            ListNode temp = p.next;
            p.next = temp.next;
            temp.next = p.next.next;
            p.next.next = temp;
            p = p.next.next;
        }
        return dummyhead.next;
    }
}

```

这是第二次做的，反倒不如第一次好。
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummyhead = new ListNode(0);
        ListNode prev = dummyhead;

        while( (head!= null) && ( (head.next!= null) ) ){
            ListNode late = head.next;
            prev.next = late;
            head.next = late.next;
            late.next = head;

            head = head.next;
            prev = prev.next.next;

        }

        return dummyhead.next;
    }
}
```
