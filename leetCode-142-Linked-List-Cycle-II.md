# 142J. Linked List Cycle II
https://leetcode.com/problems/linked-list-cycle-ii/

## 愚蠢方法
用 HashMap 秒杀，HashSet同理。 HashMap还适用于要求返回index的情况。

```Java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode p = head;
        int i = 0;
        while(p!=null){
            if(map.containsKey(p)){
                return p;
            }
            map.put(p,i);
            p = p.next;
            i+=1;
        }
        return null;
    }
}
```
## 机智方法
<pre>
快慢指针，若慢指针走了 n 步，则快指针走了 2n。
如果没有Loop不说了，如果有的话，假设loop部分长度为 b, 假设loop开始前长度为 a。
则List的长度是 a + b. 假设相遇在 a + c 处，则慢指针走了 a + c 步，而快指针
走了 a + b + c步。 a + c = n, a + b + c = 2n,解得 b = n = a + c。所以
实际List长度是 a + c + a 这么分割的。快慢数组相遇在 a + c 处。这时令一个指针重新
指向头部/dummyhead,一起移动 a 步就相遇了。此时的位置就是正确的。

注意代码中我们为了简便处理边界情况，直接使用了dummyhead。非常优秀。
</pre>
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        ListNode slow = dummyhead;
        ListNode fast = dummyhead;

        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;

        }
        if(fast == null || fast.next == null){
            return null;
        }else{
            slow = dummyhead;
            while(slow!=fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
}
```
