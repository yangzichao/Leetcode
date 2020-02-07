# 138J. Copy List with Random Pointer
https://leetcode.com/problems/copy-list-with-random-pointer/
这个题是链表里面很难的了

这个题其实也没那么难，与133有关。
## Method 最佳

<pre>
核心思想是：
先处理next的关系，就是简单的把原链表每一节原地复制一次。
然后处理random指针。
用双指针一直同时指着原节点和其复制的节点，
复制节点的random就是原节点的random的下一位。
然后再把单链表拆开成双链表。
如同DNA复制。
</pre>
```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return head;
        }
        //先在每个节点后面复制一个节点。
        Node p = head, tempP = head, copyNode = head;
        while(p != null){
            copyNode = new Node(p.val); // 新建复制节点
            tempP = p;      
            p = p.next;
            tempP.next = copyNode;
            copyNode.next = p;
        }
        p = head; // 双指针 p p2 准备开始复制random
        Node p2 = head.next;
        while(p!=null && p.next!= null){
            if(p.random!=null){ //如果random 非空 才需要复制
               p2.random =  p.random.next;
            }
            if(p2.next!=null){ //这是为了防止空指针
                              // 如果p2已经在链表最后了 就不需要移动了
             p2 = p2.next.next;
            }
            p = p.next.next; // p 将会移动到 null 但是无所谓 循环已经停止了
        }
        Node dummyhead = new Node(-1); //新建一个头部。
        Node ph = head;
        p2 = dummyhead;
        p = head.next;
        // 拆链表
        while(p.next!=null){
            ph.next = ph.next.next;
            ph = ph.next;
            p2.next = p;
            p = p.next.next;
            p2 = p2.next;
        }
        // 由于避免空指针。停止的时候链表还没有完全拆完，再手动一步。
        ph.next = null;
        p2.next = p;
        p.next = null;

        return dummyhead.next;
    }
}
```
