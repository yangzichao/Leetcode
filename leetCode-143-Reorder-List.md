# 143J. Reorder List



思想是如下：
```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head, temp = head;
        //先找到链表的中间
        while(fast!=null&&fast.next!=null){
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //然后保证后半段比较短。
        if(fast != null){
            temp = slow;
            slow = slow.next;
        }
        //把两个子链表断开
        if(temp!=null){
           temp.next = null;
        }
       //翻转后链表
        slow = reverse(slow);
        // 合并链表
        while(slow != null){
            temp = head.next;
            head.next = slow;
            slow = slow.next;
            head.next.next = temp;
            head = head.next.next;
        }
    }
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
```
