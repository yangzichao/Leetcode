# 061J?. Rotate List

what else? To not to destroy old ListNode.


Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2  
Output: 4->5->1->2->3->NULL   
Explanation:  
rotate 1 steps to the right: 5->1->2->3->4->NULL  
rotate 2 steps to the right: 4->5->1->2->3->NULL  

Example 2:

Input: 0->1->2->NULL, k = 4  
Output: 2->0->1->NULL  
Explanation:  
rotate 1 steps to the right: 2->0->1->NULL  
rotate 2 steps to the right: 1->2->0->NULL  
rotate 3 steps to the right: 0->1->2->NULL  
rotate 4 steps to the right: 2->0->1->NULL


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
