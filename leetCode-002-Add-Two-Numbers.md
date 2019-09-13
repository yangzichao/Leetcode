# 002J. Add Two Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
## Syntax:
### Java的 '?' 用法：
```Java
max = (a > b) ? a : b ;
```
等价于
```Java
if ( a > b ) {
  return a;
}else{
  return b;
}
```

### Java boolean 转 int
java 中类型转换好麻烦，这里有一个快速的办法。
```java
boolean myBoolean = true; // or false
int booleantToInt = myBoolean ? 1:0;
```
## 思路：
先看看LeetCode的 singly-linked-list 的定义：
```java
class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}
```
缺少 sentinel， 我们初始化的时候要自己创建一个没用的头。一般命名为dummyhead.
这样我们处理两个数组的第一位的时候，就可以直接用dummyhead.next()当作第一个被处理的，
这样保证数组处理的同步。

* 这个题其实很直观，之所以花费了很多功夫
是因为ListNode的Construtor, 并没有给出 next = null;
* 简单来说就是直接把两个List从前往后一对一的加起来，如果sum > 10 就需要在下一位进一。
所以sum是进位值（0 or 1） 和 两个List该位的值的和。  
即 sum = carry + p1.val + p2.val
* 用iteration的方法，需要给两个输入的List各指派一个指针p1, p2。  
还要新建一个ListNode, reList 作为返回值，并同样指派一个指针pr。
* 考虑到进位，是要再指定一个用来判断进位的变量 carry。  
carry 可以是 boolean, 也可以是整型。可以用 sum/10 来直接得到 carry的值。    
由于carry最多进一位，所以这里 carry = sum/10 显然是更方便的表达。
* 考虑到两个List 不一样长，那么我们需要判断什么时候让循环停止：  
那就是当两个List都为null的时候。
* 注意Leetcode的 ListNode 没有 next = null. 我们不能用 p1.next != null 这样的判断。  
应使用 while ( p1 != null || p2 != null) { }.
* 注意要排除空数组的存在。所以上来就要检测空数组。  
就是因为有空数组，导致我们不能贸然用p1.val, p2.val.   
我们应当设立两个局域变量 x1, x2 储存 p1.val 和 p2.val 的值。 那么怎么做到一举多得呢？  
```
int x1 = (p1!=null) ? p1.val : 0;
```
这样才能防止后面用 sum = carry + p1.val + p2.val 时 p1 p2 取不到值。
* 然后我们就按部就班的让指针指向 List 的下一位即可。
* 最后循环结束了不要忘记，如果有需要进位的话，还要再新建一节。
* 由于有dummyhead的存在，所以返回dummyhead.next. 这是容易忘记的。

## Java solution: 
```Java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode reList = new ListNode(0);
        ListNode pr = reList;
        int carry = 0;

        while(p1 != null || p2 != null) {

            int x1 = (p1 != null) ? p1.val:0;
            int x2 = (p2 != null) ? p2.val:0;
            int sum = carry + x1 + x2;

            carry = sum/10;
            sum = sum%10;

            pr.next = new ListNode(sum);
            pr = pr.next;

            if(p1 != null) {
                p1 = p1.next;
            }
            if(p2 != null){
                p2 = p2.next;
            }
        }
        if( carry == 1 ){
            pr.next = new ListNode(1);
        }
        return reList.next;
    }
}

```
