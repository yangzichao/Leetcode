# 141J. Linked List Cycle

https://leetcode.com/problems/linked-list-cycle/

最好的办法是用哈希表。其实都需要懂。
## Method 1:龟兔赛跑

首先上来判断是否是空ListNode和没有next.
然后建立 dummyhead/ Sentinel
核心是 双指针，一个一次挪一步，一个一次挪两步。
如果有循环的话，那么.next 和 .next.next永远不会为空。
所以如果任意一个为null,就应当终止循环。
如果两个指针指到同一个位置，那么就判断循环了。

!!!!!! 注意 ！！！！！！！
while 那里判断的时候 一定要先判断 p2!= null  再判断 p2.next != NULL
否则的话，如果 p2 是null的话，p2.next 就会报错！
！！！！！！！！！！！！！！
另外dummyhead 这里是冗余的。

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode p1 = dummyhead;
        ListNode p2 = dummyhead;
        while( (p1 != null) &&(p2 != null) &&(p2.next!=null)){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }
}
```
## Method 2: Set
就是把所有的节点都无脑加到一个HashSet里面，然后出现重复就报错。
缺点是运行时间似乎不如上面的方法。但是复杂度也是O(N).看来直接
移动指针还是很快的。
```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while(p != null){
            if(set.contains(p)){
               return true;
            }
            set.add(p);
            p = p.next;
        }
        return false;
    }
}
```
