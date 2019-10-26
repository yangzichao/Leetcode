# 082
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3

## Iteration
思路，核心是双指针，一前一后。后指针直接指到相同值的最后一位。
如果前指针的下一位就是后指针，说明没有重复，前指针下移一位。
如果前指针的下一位不是后指针，说明有重复，那么前指针的下一位是后指针的下一位。
因为无论如何都会有后指针移动下一位，所以下一次循环会执行将前指针移到后指针，后指针
移动一位的操作。  
为了确保后指针能够指到最后的null，大循环里应当用while(cur!= null)。cur.next!=null
的条件应当包含在内部的while循环里。

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode pre = dummyhead;
        ListNode cur = head;
        while ( cur!=null) {
            while(cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
            }
            if(pre.next == cur){
                pre = cur;
            }else{
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummyhead.next;
    }
}
```
Time: O(N), Theta(N)
Space: O(1)
## method2: recursive
可以想像一定有一个合适的recursive方法
从讨论中抄的
```java
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        if(head==null||head.next==null) return head;

        if(head.val!=head.next.val){
            head.next=deleteDuplicates(head.next);
            return head;
        }
        else{
            while(head.next!=null&&head.val==head.next.val)
                head=head.next;
            return   deleteDuplicates(head.next);
        }
    }
}
```
