# 125J. Valid Palindrome
https://leetcode.com/problems/valid-palindrome/

## Method Best:
用一前一后双指针，遇到奇特字符就跳过。
一起对位移动，如果不相同就返回false.反之通过了循环就返回true.


```java
class Solution {
    public boolean isPalindrome(String s) {
        if(s.length() == 0) return true;
        int hp = 0;
        int tp = s.length() - 1;
        while(hp < tp){
            char charH = s.charAt(hp);
            char charT = s.charAt(tp);
            if(!Character.isLetterOrDigit(charH)){
                hp++;
                continue;
            }
            
            if(!Character.isLetterOrDigit(charT)){
                tp--;
                continue;
            }
            
            if(Character.toLowerCase(charH) != Character.toLowerCase(charT)){
                return false;
            }
            hp++;
            tp--;

        }
        return true;
    }
}
```