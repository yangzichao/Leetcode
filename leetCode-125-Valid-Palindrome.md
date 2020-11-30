# 125J. Valid Palindrome

https://leetcode.com/problems/valid-palindrome/

## Method Best:

用一前一后双指针，遇到奇特字符就跳过。
一起对位移动，如果不相同就返回 false.反之通过了循环就返回 true.

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

第二次写确实好一点嗯
以下是 O(1) Space

```java
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        for(int l = 0, r = s.length() - 1; l < r; l++, r-- ) {
            while(l < r && !Character.isLetterOrDigit(s.charAt(l)) ) {
                l++;
            }
            while(l < r && !Character.isLetterOrDigit(s.charAt(r)) ) {
                r--;
            }
            if(s.charAt(l) != s.charAt(r)) {
                return false;
            }
        }
        return true;
    }
}
```

但是改成这样之后会更快，因为避免了过多的 call build in function 啊

```java
class Solution {
    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        for(int l = 0, r = s.length() - 1; l < r; l++, r-- ) {
            while(l < r && !Character.isLetterOrDigit( chars[l] ) ) {
                l++;
            }
            while(l < r && !Character.isLetterOrDigit( chars[r] ) ) {
                r--;
            }
            if( chars[l] != chars[r] ) {
                return false;
            }
        }
        return true;
    }
}
```
