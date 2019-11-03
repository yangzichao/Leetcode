# 328J. Odd Even Linked List

https://leetcode.com/problems/odd-even-linked-list/



## Method Best:
<pre>
分析:
需要四个指针，两个用来固定两个子链表头部，两个用来往后指。
需要考虑的主要是如何结尾，终止while循环。
考虑两种临界情况，    
1: 奇 -> 偶 -> 空        
  odd   even       
2: 偶 -> 奇 -> 空       
  even  odd           
我们需要的是，odd 指针指在最后一个奇数上，even指针指在null或者even.next为空。
这样odd.next = evenHead就可以构造一个完整的链表。
所以上述的情形 1 是已经 可以结束循环了，代表even.next== null
而情形2 还需要再运行一次，直到        
偶 -> 奇 -> 空                  
     odd  even
此时意味着 even == null.  
综上while里面应当是 even!=null && even.next != null      
</pre>



```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while( even!=null && even.next!=null  ){
            odd.next = odd.next.next; // 也可以   odd.next = even.next;
            odd = odd.next;
            even.next = even.next.next; // 也可以   odd.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
```
