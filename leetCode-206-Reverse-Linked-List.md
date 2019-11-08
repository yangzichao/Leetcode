# 206J. Reverse Linked List
* what to do? think about recursion method.

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL  
Output: 5->4->3->2->1->NULL

Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both? No

最好的方法是破坏性的iteration; T O(N) S O(1)
recursive 比较优雅一点。
## Method 1: Iteration Non-destructive

Class Learned:
* 可以用 ListNode p = null; 来新建空指针。
* 其实很简单，我们需要两个指针来指同一个链表。用一个复制对应的Node并链接另一个。  
再把另一个重新指回新List。

```Java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode pr = null;
        ListNode pr2 = pr;

        while(p != null) {
            pr = new ListNode(p.val);
            pr.next = pr2;
            pr2 = pr;
            p = p.next;
        }

        return pr;
    }
}
```
## Method 2 : Iteration destructive
核心的思想还是从前到后转变指针方向，然后返回最后一个指针。
即 1 -> 2 -> 3;    
1 <- 2 <- 3;     
从3 开始返回    

用破坏的方法也可以
原理是运用双指针。
* 先排除空链表和只有一个元素的链表，直接返回。
* 然后用两个指针p p2固定住前两节，并且直接把第一节断开。
* p2 进行循环的时候，需要用一个temp ListNode把p2.next固定住，以防被垃圾回收找不到地址。
* 这样将指针排好序复位即可。

Iteration的方法只能够从前到后改变Node的指向，不能够回退。若想回退是需要用recursion的。


```Java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode p2 = head.next;
        p.next = null;
        while (p2!=null){
            ListNode temp = p2.next;
            p2.next = p;
            p = p2;
            p2 = temp;
        }
        return p;
    }
}
```
代码二，原理相同，相对更优雅。是原答案。
* prev的效果相当于dummyhead, 因此规避了问题。
```Java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
}

```
## Method 3: Recursion
<pre>
* Recursive 方法实际上是将 指针从后往前翻转。
  * 1 -> 2 -> 3 -> 4 -> 5
  * 1 -> 2 -> 3 -> 4 <-> 5  即 5 指回 4
  * 1 -> 2 -> 3 -> 4 <- 5   即 4 下一个指向 null
  * 1 -> 2 -> 3 <-> 4 <- 5  即 4 指回 3
  * 1 -> 2 -> 3 <- 4 <- 5   即 3 下一个指向 null
  * 以此类推
* 判断中有两个语句， head == null 是用来排除空ListNode的。
head.next == null 是判断取到了最后一个Node的。
*   ListNode p = reverseList(head.next); 相当于是直接将p指向了最后一位。
*   第一个返回的 p 是 List的最后一节。此时head指向倒数第二节。head.next 其实就是 p.
但是不能用 p.next 代替 head.next. 因为 p 的位置事实上是不变的，变的只是 head。
* p 一直指着原List的末尾，而head不停的回退，并且每一步都先向下连next两步自指，
然后将自己的下一步指向null. 这样就成功的翻转了一节。由于递归，回自动回到上一节。

从后往前逆转List的指针，是只能用递归的。

注意： 代码中的 head == null 判断只是为了防止空head输入。真正重要的
是 head.next == null 的判断。这使得 p 一直指在尾部node，而不是null.
</pre>
```Java
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }else{
      ListNode p = reverseList(head.next);
      head.next.next = head;
      head.next = null;
      return p;
    }
}
```

## Java Visualizer
这个代码进入Java Visualizer 可以直观感受这个递归是怎么工作的。
```Java
public class ListNode{
   public int val;
   public ListNode next;
   public ListNode(int n){
      val = n;
   }
   public void add(int n){
      ListNode p = this;
      while(p.next!=null){
         p = p.next;
      }
      p.next = new ListNode(n);
   }
   static ListNode reverse(ListNode head) {
      if (head.next == null) return head;
      ListNode last = reverse(head.next);
      head.next.next = head;
      head.next = null;
      return last;
   }
   public static void main(String[] args) {
      ListNode test = new ListNode(1);
      test.add(2);
      test.add(3);
      test.add(4);
      test.add(5);
      ListNode k = reverse(test);
   }
}

```
