# 083J?. Remove Duplicates from Sorted List

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2  
Output: 1->2   

Example 2:

Input: 1->1->2->3->3  
Output: 1->2->3

## Method 1: Iteration
这个题的思想和 [026](leetCode-026-Remove-Duplicates-from-Sorted-Array.md)一样。

### Java Solution:
```Java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null ){
            return null;
        }
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode p = head;
        while( p != null) {
            if(p.val != head.val){
                head = head.next;
                head.val = p.val;
            }
            p = p.next;
        }
        head.next = null;
        return dummyhead.next;

    }
}

```
