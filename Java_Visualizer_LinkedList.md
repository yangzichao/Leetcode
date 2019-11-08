# LinkedList for Java Visualizer
https://cscircles.cemc.uwaterloo.ca/java_visualize/
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

   public void add(int[] a){
     for(int n: a){
       this.add(n);
     }
   }

   public static void main(String[] args) {
     int[] a = new int[]{1,2,3,4,5};
     ListNode test = new ListNode(0);
     test.add(a);
   }
}
```
