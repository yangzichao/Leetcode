# 071J. Simplify Path
https://leetcode.com/problems/simplify-path/

这个题主要要解决的痛点就是输入中无用的 "." 和重复的 "/"
以及需要被运算的上一级运算符 ".."

注意 "..." 是可行的，因为可以被作为文件名。
## Method: almost Best

```Java
class Solution {
    public String simplifyPath(String path) {
        //首先需要处理的就是重复的 "/"，用这个方法就解决了
        String[] string = path.split("/");
        Stack<String> stack = new Stack<>();
        // 其次需要解决的是 ".." 和 "." 和 ""
       for(String s : string){
           // ".." 会往上退
           if( !stack.empty() && s.equals("..") ){
               stack.pop();
           }
           // "." "" 没有用 应当忽略，这里应该记录有用的输入
           if( !s.equals(".") && !s.equals("") && !s.equals("..")){
               stack.push(s);
           }
       }
        // 此时stack里面是正确的输入顺序。建立新的String就好了
        String ans = "";
        for(String s : stack){
            ans = ans + "/"+ s;
        }
       // 防止最后结果为空，应当返回/
        return ans.equals("") ? "/" : ans;

    }
}
```
