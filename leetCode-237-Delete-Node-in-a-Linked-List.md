# 237J. Delete Node in a Linked List

Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Given linked list -- head = [4,5,1,9]


Example 1:

Input: head = [4,5,1,9], node = 5  
Output: [4,1,9]  

Explanation: You are given the second node with value 5,
the linked list should become 4 -> 1 -> 9 after calling your function.

Example 2:

Input: head = [4,5,1,9], node = 1
Output: [4,5,9]

Explanation: You are given the third node with value 1,
the linked list should become 4 -> 5 -> 9 after calling your function.

## 解析：
* 这个题描述不清。实际上只是让你解决一个很小的问题：
  * 给你一个链表，然后现在你的指针指着一个Node.让你从链表中删掉这个Node。
* 一般来说删掉一个Node, 比较轻松的办法是让指针指在被删Node之前。这个题的意义就在这里。
* 方法是，先取下一个Node的值，再把下一个Node给跳过去就可以了。
* 题目已经排除了是链表尾部的可能性。所以不用担心null的处理。

**这种办法不能处理链表尾部，所以一般删除节点，指针还是应当指向被删节点的上一个Node。**
### Java Solution
```Java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```
