# 680 Valid-Palindrome-II

difficulty: Easy

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>
Given a non-empty string <code>s</code>, you may delete <b>at most</b> one character.  Judge whether you can make it a palindrome.
</p>
<p><b>Example 1:</b><br>
</p><pre><b>Input:</b> "aba"
<b>Output:</b> True
</pre>
<p></p>
<p><b>Example 2:</b><br>
</p><pre><b>Input:</b> "abca"
<b>Output:</b> True
<b>Explanation:</b> You could delete the character 'c'.
</pre>
<p></p>
<p><b>Note:</b><br>
</p><ol>
<li>The string will only contain lowercase characters a-z.
The maximum length of the string is 50000.</li>
</ol>
<p></p></div></section>
 
 ## Method One 
 核心就一句话，一旦碰到两边不一样了，那么我们试试，去掉左边或者右边之后，是否仍然是 palindrome 的。
``` Java
class Solution {
    public boolean validPalindrome(String s) {
        for(int l = 0, r = s.length() - 1; l < r; l++, r-- ) {
            if(s.charAt(l) == s.charAt(r)) continue;
            return isPalindrome( s.substring(l, r) ) || isPalindrome(l+1, r+1);
        }
        return true;
    }
    public boolean isPalindrome(String s) {
        for(int left = 0, right = s.length() - 1; left < right; left++, right-- ) {
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
        }
        return true;
    }
}
​
```
