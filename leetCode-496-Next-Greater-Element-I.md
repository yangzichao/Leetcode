# 496J. Next Greater Element I
https://leetcode.com/problems/next-greater-element-i/

本题是 monotonous stack 的经典
以下为摘抄：

单调递增栈可以找到左起第一个比当前数字小的元素。比如数组 [2 1 4 6 5]，刚开始2入栈，数字1入栈的时候，发现栈顶元素2比较大，将2移出栈，此时1入栈。那么2和1都没左起比自身小的数字。然后数字4入栈的时候，栈顶元素1小于4，于是1就是4左起第一个小的数字。此时栈里有1和4，然后数字6入栈的时候，栈顶元素4小于6，于是4就是6左起第一个小的数字。此时栈里有1，4，6，然后数字5入栈的时候，栈顶元素6大于5，将6移除，此时新的栈顶元素4小于5，那么4就是5左起的第一个小的数字，最终栈内数字为 1，4，5。

单调递减栈可以找到左起第一个比当前数字大的元素。这里就不举例说明了，同样的道理，大家可以自行验证一下。

## 方法 最佳 monotonous stack 单调栈
<pre>
这个思路很机智，我们不管nums1, 先只用nums2,
把所有的nums[i]的左起第一个比自己大的都找出来存进map中。
出入栈过程是：
nums2[i]如果比栈上面的小，那么就入栈；反之，就把栈全部出空，并加入
map中，value = nums2[i]。
iteration结束最后剩在stack里的，说明没有左起比自己大的，
全部加到map里value = -1.
</pre>

```Java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> myStack = new Stack<>();
        Map<Integer,Integer> myMap = new HashMap<>();
        int[] re = new int[nums1.length];
        for(int n : nums2){
            while(!myStack.empty() && n > myStack.peek()){
                myMap.put(myStack.pop(), n);
            }
            myStack.push(n);
        }
        while(!myStack.empty()){
             myMap.put(myStack.pop(),-1);
        }
        for(int i = 0; i < nums1.length; i++){
            re[i] = myMap.get(nums1[i]);
        }
        return re;
    }
}
```
