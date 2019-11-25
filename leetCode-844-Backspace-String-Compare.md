# 844J. Backspace String Compare
https://leetcode.com/problems/backspace-string-compare/


## Method 1 : 直接方法
用栈把两个数组都用stack处理一遍，然后对比。

```Java
class Solution {
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String S) {
        Stack<Character> ans = new Stack();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }
}
```
## Method 2: Two Pointers

其原理是 从后往前比较

```Java
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int skipS = 0, skipT = 0; //记录需要跳过多少位。
        int ps = S.length() - 1, pt = T.length() - 1; // s的指针，t的指针
        while(ps >= 0 || pt >= 0 ){ // 两个字符串都比较完才停止。
            while(ps >= 0){//防止 ps 已经被判定完
                if(S.charAt(ps) == '#'){
                    skipS++; //准备跳过一个字符
                    ps--; // 指向下一个
                }else if(skipS > 0){ // 说明有需要被跳过的字符
                    skipS--;//跳过这个字符
                    ps--;
                }else{
                    break; // 这是一个正常字符，停止内部循环，准备比较。
                }
            }
            // t 和 s 一样
            while(pt >= 0){//防止 pt 已经被判定完
                if(T.charAt(pt) == '#'){
                    skipT++; //准备跳过一个字符
                    pt--; // 指向下一个
                }else if(skipT > 0){ // 说明有需要被跳过的字符
                    skipT--;//跳过这个字符
                    pt--;
                }else{
                    break; // 这是一个正常字符，停止内部循环，准备比较。
                }
            }
            //字符不同
            if(ps >= 0 && pt >= 0 && S.charAt(ps) != T.charAt(pt)){
                return false;
            }
            //字符串有效长度不一
            if((ps >= 0) != (pt >= 0)){
                return false;
            }
            //比较下一位
            ps--;
            pt--;
        }

        return true;
    }
}
```
