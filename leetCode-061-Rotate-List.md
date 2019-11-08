# 061J?. Rotate List

https://leetcode.com/problems/rotate-list/


## Method 1: iteration.

* 先将List连一圈，同时也能得到List长度。
* 不用dummyhead的弊端是，需要上来就判断是否是空List.
* counter = counter - k%counter; 这里是考虑了 k > 链表长度的情况。
* 剩下的都是基本操作。

### java solution
```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if ( head == null ){
            return head;
        }

        ListNode p = head;
        int counter = 1;
        while(p.next != null) {
            p = p.next;
            counter += 1;
        }
        counter = counter - k%counter;

        p.next = head;
        while ( counter > 0) {
            counter -= 1;
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;

    }
}

```
