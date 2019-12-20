# 301J. Remove Invalid Parentheses
https://leetcode.com/problems/remove-invalid-parentheses/

这个题非常的磨人，令人感到烦躁。
## Method: Best 
<pre>

</pre>
```java
class Solution {
    public boolean isValid(String s){
        int count = 0;
        for(int i = 0; i < s.length();i++){
            char c = s.charAt(i);
            if(c == '(') count++;
            if(c == ')') count--;
            if(count < 0) return false; 
            // means the parentheses are already not closed
            // return false immediately
        }
        return count == 0;
    }
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>(); // answer list
        
        // represent how many left/right parentheses need to be removed
        int l = 0, r = 0;
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            l += c == '(' ? 1: 0;
            if(l == 0){
                //如果左右括号配好对了，第一个出现的是),那么这个是要移除的
                r += c == ')' ? 1:0;
            }else{
                //如果没配好对，如果有有括号，那么就减少一个未配对的。
                l-= c == ')' ? 1:0;
            }
        }
        
        dfs(s,0,l,r,ans);
        return ans;
    }
    
    public void dfs(String s, int start, int l, int r, List<String> ans){
        // 如果合法 就加入答案
        if( l == 0 && r == 0){
            if(isValid(s)){
                ans.add(s);
            }
            return;
        }
        
        //
        for(int i = start; i < s.length(); i++){
            char c = s.charAt(i);
            //这个判断是后面加上的，我们对于重复的部分只删一个且只删第一个。
            if(i != start && c == s.charAt(i-1)) continue;
            //其他字符自动跳过
            //先删右括号，再删左括号，为什么呢？
            // 考虑这么一件事，如果左括号比右括号多，而且只需要删左括号，说明
            // 是这种情况 (((((((()
            // 在需要删左括号的情况下 仍然需要删右括号，说明什么？
            // 说明有多的右括号出现在一串左括号前面了 即 ) (((((((()
            if(r > 0 && c == ')'){
                String temp = i < s.length() ? s.substring(0,i) + s.substring(i+1) : s.substring(0,i);
                dfs(temp, i, l, r-1, ans);
            }else if(l > 0 && c == '('){
                String temp = i < s.length() ? s.substring(0,i) + s.substring(i+1) : s.substring(0,i);
                dfs(temp, i, l-1, r, ans);
            }
            // if(c == '(' || c ==')'){
            //     //这是用来删除第i个字符。
            //     String temp = i < s.length() ? s.substring(0,i) + s.substring(i+1) : s.substring(0,i);
            //     if(r > 0 && c ==')'){
            //         dfs(temp, i, l, r-1, ans);
            //     }else if(l > 0 && c == '('){
            //         dfs(temp, i, l-1, r, ans);
            //     }
            // }
        }
    }
}
```