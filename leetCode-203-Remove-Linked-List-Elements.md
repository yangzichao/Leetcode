# 203J. Remove Linked List Elements
https://leetcode.com/problems/remove-linked-list-elements/

## Method Best
这个题很简单…… 就不多说了

```Java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) {return null;}
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode prev = dummyhead;
        while(head!=null){
            if(head.val == val){
                prev.next = head.next;
                head.next = null;
                head = prev.next;
            }else{
                prev = prev.next;
                head = head.next;
            }
        }
        return dummyhead.next;
    }
}
```
