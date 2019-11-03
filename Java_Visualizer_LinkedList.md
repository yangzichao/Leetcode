# LinkedList for Java Visualizer

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
   public void add
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
