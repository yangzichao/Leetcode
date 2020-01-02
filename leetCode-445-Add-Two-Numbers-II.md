# 445J. Add Two Numbers II
https://leetcode.com/problems/add-two-numbers-ii/

可以先反转链表再和[002](leetCode-002-Add-Two-Numbers.md)一样。

## Method 1: 用栈

为了不反转链表，先用栈把元素存下来再加一起。

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();        
        int size1 = getL(s1,l1);
        int size2 = getL(s2,l2);
        int carry = 0;
        ListNode ans = new ListNode(-1);
        while(!s1.isEmpty() || !s2.isEmpty()){
            int val1 = s1.size() == 0 ? 0 : s1.pop().val;
            int val2 = s2.size() == 0 ? 0 : s2.pop().val;
            int val = (val1 + val2 + carry)%10;
            carry = (val1 + val2 + carry) /10;
            ListNode temp = new ListNode(val);
            temp.next = ans.next;
            ans.next = temp;
        }
        if(carry > 0){
            ListNode temp = new ListNode(carry);
            temp.next = ans.next;
            ans.next = temp;            
        }
        return ans.next;
    }
    
    public int getL(Stack s, ListNode l){
        int count = 0;
        while(l!=null){
            s.push(l);
            l = l.next;
            count+=1;
        }
        return count;
    }
}
```