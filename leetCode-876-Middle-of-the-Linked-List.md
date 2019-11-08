# 876J. Middle of the Linked List
https://leetcode.com/problems/middle-of-the-linked-list/


这是一道基础题，在很多需要取中位的题里都很有用。



## 最佳方法
<pre>
用快慢指针。
运行k次循环后，慢指针指向 1+k th， 快指针指向 1+2k th.
考虑两种情形:
1.odd
odd总共 1+2n 个节点。运行n次循环，快指针.next == null;慢指针在n+1个，是正中间的中位数。
2.even
even 总共2n个几点，运行n次循环，快指针 == null;  慢指针在 n+1个，是下半区第一个。
所以条件为 fast!=null && fast.next!=null


</pre>

这次竟然和答案一字不差。
```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```
