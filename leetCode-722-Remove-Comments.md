# 722 Remove-Comments 
 
difficulty: Medium 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given a C++ program, remove comments from it. The program <code>source</code> is an array where <code>source[i]</code> is the <code>i</code>-th line of the source code.  This represents the result of splitting the original source code string by the newline character <code>\n</code>.</p>
<p>In C++, there are two types of comments, line comments, and block comments.</p>
<p>
The string <code>//</code> denotes a line comment, which represents that it and rest of the characters to the right of it in the same line should be ignored.
</p><p>
The string <code>/*</code> denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of <code>*/</code> should be ignored.  (Here, occurrences happen in reading order: line by line from left to right.)  To be clear, the string <code>/*/</code> does not yet end the block comment, as the ending would be overlapping the beginning.
</p><p>
The first effective comment takes precedence over others: if the string <code>//</code> occurs in a block comment, it is ignored. Similarly, if the string <code>/*</code> occurs in a line or block comment, it is also ignored.
</p><p>
If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.
</p><p>
There will be no control characters, single quote, or double quote characters.  For example, <code>source = "string s = "/* Not a comment. */";"</code> will not be a test case.  (Also, nothing else such as defines or macros will interfere with the comments.)
</p><p>
It is guaranteed that every open block comment will eventually be closed, so <code>/*</code> outside of a line or block comment always starts a new comment.
</p><p>
Finally, implicit newline characters can be deleted by block comments.  Please see the examples below for details.
</p>
<p>After removing the comments from the source code, return the source code in the same format.</p>
<p><b>Example 1:</b><br>
</p><pre style="white-space: pre-wrap"><b>Input:</b> 
source = ["/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"]
The line by line code is visualized as below:
/*Test program */
int main()
{ 
  // variable declaration 
int a, b, c;
/* This is a test
   multiline  
   comment for 
   testing */
a = b + c;
}
<b>Output:</b> ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
The line by line code is visualized as below:
int main()
{ 
int a, b, c;
a = b + c;
}
<b>Explanation:</b> 
The string <code>/*</code> denotes a block comment, including line 1 and lines 6-9. The string <code>//</code> denotes line 4 as comments.
</pre>
<p></p>
<p><b>Example 2:</b><br>
</p><pre style="white-space: pre-wrap"><b>Input:</b> 
source = ["a/*comment", "line", "more_comment*/b"]
<b>Output:</b> ["ab"]
<b>Explanation:</b> The original source string is "a/*comment<b>\n</b>line<b>\n</b>more_comment*/b", where we have bolded the newline characters.  After deletion, the <i>implicit</i> newline characters are deleted, leaving the string "ab", which when delimited by newline characters becomes ["ab"].
</pre>
<p></p>
<p><b>Note:</b>
</p><li>The length of <code>source</code> is in the range <code>[1, 100]</code>.</li>
<li>The length of <code>source[i]</code> is in the range <code>[0, 80]</code>.</li>
<li>Every open block comment is eventually closed.</li>
<li>There are no single-quote, double-quote, or control characters in the source code.</li>
<p></p></div></section>
 
 ## Method One 
 
可以学习一下RegExr
https://leetcode.com/problems/remove-comments/discuss/109195/1-liners

``` Java
class Solution {
    public List<String> removeComments(String[] source) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean isBlock = false;
        for(String s : source){
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(isBlock){
                    // 如果发现在 block 里面，其实我们什么都不用做。
                    // 唯一需要做的就是检测有木有 block 终结的符号。
                    if( i < s.length() - 1 && c == '*' && s.charAt(i+1) == '/'){
                        isBlock = false;
                        i++; // i++ 是为了跳过 不仅仅 * 还有/.
                    }
                    
                }else{
                    // 如果不在 block 里面，只需要注意 // 就可以了
                    // 当然还需要注意 /* 开始一个新的block
                    if( i < s.length() - 1 && c == '/' && s.charAt(i+1) == '*'){
                        isBlock = true;
                        i++; // i++ 是为了跳过 不仅仅 * 还有/.
                        continue;
                    }else if ( i < s.length() - 1 && c == '/' && s.charAt(i+1) == '/'){
                        break;
                    }else{
                        // 注意了，这个题目这里，如果遇到除掉 // 之后，该行是只有空格的情况是不予额外处理的。
                        // 如果要求处理那种情况，这个题就非常难了。但是一般是不要求这么处理的。
                        sb.append(c);
                    }
                }
            }
            if(!isBlock && sb.length() > 0){
                ans.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return  ans;
    }
}
​
```