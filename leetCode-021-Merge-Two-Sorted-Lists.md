# 021J. Merge Two Sorted Lists
What to do?
* Think about the recursion method*
这题跟 [Merge two binary tree](https://leetcode.com/problems/merge-two-binary-trees/) 竟然有相似的地方，惊不惊喜意不意外。

> Merge two sorted linked lists and return it as a new list.            
The new list should be made by splicing together the nodes of the first two lists.

> Example:
> Input: 1->2->4, 1->3->4  
Output: 1->1->2->3->4->4


## Method 1: Interation

###  Analysis:
* From [002](leetCode-002-Add-Two-Numbers.md), we've learned something:
  * 用 p1 != null 来判断，而不是用 p1.next != null 来判断。
在leetcode 的ListNode 定义中，如果 p1.next 是空， p1 = p1.next 是合法的，而p1.next 是不合法的。
  * 直接建立 dummyhead, 并为l1,l2,dummyhead建立三个指针， p1, p2, pd.
  * 从 002 中我们知道，两个数组，停止循环的节点如果不同步，可以用
while(p1!=null || p2!= null) 来判断。
  * 从 002 中我们还知道，取两个数组的值，为了安全起见，不能直接调用 int x1 = p1.val. 应当先判断指针是否为空。
* 剩下的部分就是，判断指向两个数组的指针对应的val的大小，将小的值（相等的话随意）生成新的一节返回给dummyhead，然后将传递了值的数组的指针指向下一位。注意数组是否是非空。

### Java Solution:

```Java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyhead = new ListNode(0);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode pd = dummyhead;
        while (p1 != null || p2 != null) {
            int x1 = (p1 != null) ? p1.val:Integer.MAX_VALUE;
            int x2 = (p2 != null) ? p2.val:Integer.MAX_VALUE;

            if ( x1 < x2 ) {
                pd.next = new ListNode(x1);
                if( p1 != null ){
                    p1 = p1.next;
                }
            }else {
                pd.next = new ListNode(x2);
                if( p2 != null ){
                    p2 = p2.next;    
                }
            }
            pd = pd.next;
        }
        return dummyhead.next;
    }
}
```

第二次做的解法.这个解法没问题，但是可以写的更简化。
核心思想是：不需要令循环包含有一个到null的情况。可以强制要求两个有任何一个是null就停止循环。
然后将剩余的加到结尾。

```Java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 ==null){
            return l1 == null? l2:l1;
        }
        ListNode dummyhead = new ListNode(0);
        ListNode p = dummyhead;
        while(l1!=null||l2!=null){
            if(l1.val > l2.val){
                p.next = l2;
                p = p.next;
                l2 = l2.next;
                if(l2 == null){
                    p.next = l1;
                    break;
                }
            }else{
                p.next = l1;
                p = p.next;
                l1 = l1.next;
                if(l1 == null){
                    p.next = l2;
                    break;
                }
            }
        }
        return dummyhead.next;
    }
}
```

第三次的代码

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(l1!=null || l2 != null){
            if(l1 == null){
                p.next = l2;
                break;
            }
            if(l2 == null){
                p.next = l1;
                break;
            }
            if(l1.val < l2.val){
               p.next = l1;
               l1 = l1.next;
            }else{
               p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
            p.next = null;
        }
        return dummy.next;
    }
}
```

## Method 2 : Recursion

### Java Solution
This is copied from discussion.

```Java
public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}
```
