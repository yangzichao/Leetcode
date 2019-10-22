# 058J. Length of Last Word


Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5


## Method 1:
Note: 这个问题的难点在于处理结尾的空格和连续空格。

以下代码的逻辑是，用space = T/F 标记是否出现了空格；
如果当下不是非空格字符，查看space标记，说明前面有没有
空格符。如果有，则单词长度归一，标记标false, 反之
单词长度加一。
```Java
class Solution {
    public int lengthOfLastWord(String s) {
        int lengthLast = 0;
        boolean space = false;
        for(int i = 0; i < s.length(); i+=1){
            if(s.charAt(i) == ' '){
                space = true;
            }
            else{
                if(space){
                    lengthLast = 1;
                    space = false;
                }else{
                    lengthLast += 1;
                }
            }
        }
        return lengthLast;
    }
}
```
T: O(N)
S: O(1)
## Method 2:
上面的问题是从头到尾扫描，但是我们只需要字符结尾，
从尾扫描更快。而且逻辑更简单。忽略所有的空格符。
用一个boolean标记是否已经出现了末尾单词，如果已经出现了末尾单词
并且又遇到了空格，那么直接跳出循环。
双100%。
```Java
class Solution {
    public int lengthOfLastWord(String s) {
        int lengthLast = 0;
        boolean meetWord = false;

        for(int i = s.length()-1; i > -1; i-=1){
            if(s.charAt(i)!=' '){
                meetWord = true;
                lengthLast += 1;
            }else{
                if(meetWord){
                    break;
                }
            }
        }
        return lengthLast;
    }
}
```
