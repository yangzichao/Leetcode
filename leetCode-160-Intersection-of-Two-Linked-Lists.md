# 160J. Intersection of Two Linked Lists
https://leetcode.com/problems/intersection-of-two-linked-lists/



## Method1 HashSet
用Hash表很简单。

```Java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> mySet = new HashSet<>();
        while(headA!=null){
            mySet.add(headA);
            headA = headA.next;
        }
        while(headB!=null){
            if(mySet.contains(headB)){
                return headB;
            }else{
                headB = headB.next;
            }
        }
        return null;
    }
}
```
# Method2: Even better!
这个方法也很惊艳！
https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
<pre>
原理是 设链表1 长度为 a+c 链表2 长度为 b+c
c是他们共有的部分的长度。指针p1 p2 一起走，每次都同时走一步。
如果指针 p1 走到1的尽头，就让它走到2的头部，p2走到2的尽头，你就让它走到1的头部。
这样指针p1 走过 a+c+b, p2 走过 b + c + a。
每次都只走一步的话他们一定会在交汇处相遇。如果没有交汇处呢？说明 c = 0. 他们会
走 a+b 和 b+a 并且终止于 null. 他们还是相等。
</pre>

```Java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //boundary check
    if(headA == null || headB == null) return null;

    ListNode a = headA;
    ListNode b = headB;

    //if a & b have different len, then we will stop the loop after second iteration
    while( a != b){
    	//for the end of first iteration, we just reset the pointer to the head of another linkedlist
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }

    return a;
}
```
