# 1209J. Remove All Adjacent Duplicates in String II

https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

## Method

这个题吧，理解题意更重要，k 的 意思是只删除 k 个，不是删除 k 以上个。
我们用两个 stack 一个记录 character ,一个记录频率。

```java
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        for( char c : s.toCharArray()) {
            if( charStack.isEmpty() || charStack.peek() != c ) {
                charStack.push(c);
                countStack.push(1);
                continue;
            }
            countStack.push(countStack.pop() + 1);
            if(countStack.peek() == k) {
                countStack.pop();
                charStack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while( !charStack.isEmpty() ) {
            char c = charStack.pop();
            int fre = countStack.pop();
            for(int i = 0; i < fre; i++) {
                sb.append(c);
            }
        }
        return sb.reverse().toString();
    }
}
```

可以只记录频率的其实。
