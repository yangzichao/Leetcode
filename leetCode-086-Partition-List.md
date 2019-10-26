# 086J. Partition List

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5



## Method1
分析：
直接新建两个列表拼起来是最简单的。
之前试图在原数组上织布，细节处理失败了。

```Java
class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null){
            return head;
        }
        ListNode preHead = new ListNode(0);
        ListNode pre = preHead;
        ListNode curHead = new ListNode(0);
        ListNode cur = curHead;

        while(head!=null){
            if(head.val < x){
                pre.next = head;
                pre = pre.next;
            }else{
                cur.next = head;
                cur = cur.next;
            }
            head = head.next;
        }
        cur.next = null; // 这一步是必不可少的，防止末尾形成loop。
        pre.next = curHead.next;
        return preHead.next;
    }
}
```
