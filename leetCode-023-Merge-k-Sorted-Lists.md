# 023J. Merge k Sorted Lists
https://leetcode.com/problems/merge-k-sorted-lists/

优先队列的方法也是值得试一试的。
## Method Best, merge with divide and conquer.
<pre>
思路是分治合并。即先实现两个列表的合并，然后合并剩下的。
N是总的元素个数，k是链表的个数，时间复杂度是O(Nlogk),空间 O(1)
</pre>
```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummyhead = new ListNode(-1);
        ListNode p = dummyhead;
        ListNode p1 = l1, p2 = l2;
        while(p1!=null||p2!=null){
            if(p1 == null){
                p.next = p2;
                break;
            }
            if(p2 == null){
                p.next = p1;
                break;
            }
            if(p1.val < p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
            p.next = null;
        }
        return dummyhead.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length < 1){
            return null;
        }
        int interval = 1;
        while(interval < lists.length){
            for(int i = 0; i + interval < lists.length; i += interval*2){
                lists[i] = mergeTwoLists(lists[i],lists[i+interval]);
            }
            interval*=2;
        }
        return lists[0];
    }
}
```
