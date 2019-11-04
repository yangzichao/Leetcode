# 369J. Plus One Linked List
https://leetcode.com/problems/plus-one-linked-list/

## 解法1 不是很优雅
和066我个人写的一样。
优点是可以处理非加1的情况。缺点是代码比较啰嗦。
原理是从后往前处理，即从个位起处理。
原则就是在判断是否需要进位。
```Java
class Solution {
    public static ListNode reverse(ListNode l){
        if(l == null || l.next == null){
            return l;
        }
        ListNode p = reverse(l.next);
        l.next.next = l;
        l.next = null;
        return p;
    }
    public ListNode plusOne(ListNode head) {
        ListNode rev = reverse(head);
        ListNode p = rev;
        int carry = 0;
        p.val += 1;
        while(p!=null){
            if(p.val + carry > 9){
                p.val = p.val + carry - 10;
                carry = 1;
                p = p.next;
            }else{
                p.val += carry;
                carry = 0;
                return reverse(rev);
            }
        }
        // 能跳出while说明carry 必然为1
        p = new ListNode(1);
        p.next = reverse(rev);
        return p;

    }
}
```

## 解法1改进
承继自[066](leetCode-066-Plus-One.md)
```Java

class Solution {
    public static ListNode reverse(ListNode l){
        if(l == null || l.next == null){
            return l;
        }
        ListNode p = reverse(l.next);
        l.next.next = l;
        l.next = null;
        return p;
    }
    public ListNode plusOne(ListNode head) {
        ListNode rev = reverse(head);
        ListNode p = rev;

        while(p!=null){
            if(p.val < 9){
                p.val++;
                return reverse(rev);
            }
            p.val = 0;
            p = p.next;
        }
        // 能跳出while说明carry 必然为1
        p = new ListNode(1);
        p.next = reverse(rev);
        return p;

    }
}
```
