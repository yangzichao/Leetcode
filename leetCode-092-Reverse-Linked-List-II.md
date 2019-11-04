# 092J(1/1). Reverse Linked List II

https://leetcode.com/problems/reverse-linked-list-ii/





## 方法1 不一定好，比较好解释。
<pre>
首先实现反转前N个的方法，但是不能和后面断掉，所以我们需要定义一个successor,
指向不需要被翻转的头部。
然后我们将指针挪到需要被翻转的node的前一位，使用
      p.next = reverseFirstN(p.next,n);
这样做的道理是因为我们无法把dummyhead传到迭代method里面。

</pre>
```Java
class Solution {
    ListNode successor = null;
    public ListNode reverseFirstN(ListNode l, int n){
        if( n == 1) {
            successor = l.next;
            return l;
        }
        ListNode p = reverseFirstN(l.next,n - 1);
        l.next.next = l;
        l.next = successor;
        return p;
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode p = dummyhead;
        while(m > 1){
            p = p.next;
            m -=1;
            n -=1;
        }
        p.next = reverseFirstN(p.next,n);
        return dummyhead.next;
    }
}

```
