# 186 Reverse-Words-in-a-String-II

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
<div><p>Given an input string<strong><em>&nbsp;</em></strong>, reverse the string word by word.&nbsp;</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:  </strong>["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
<strong>Output: </strong>["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]</pre>
<p><strong>Note:&nbsp;</strong></p>
<ul>
	<li>A word is defined as a sequence of non-space characters.</li>
	<li>The input string does not contain leading or trailing spaces.</li>
	<li>The words are always separated by a single space.</li>
</ul>
<p><strong>Follow up:&nbsp;</strong>Could you do it <i>in-place</i> without allocating extra space?</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private char[] chars;
    public void reverseWords(char[] s) {
        this.chars = s;
        reverse(0, s.length - 1);
        int left = 0;
        for(int i = 0; i < chars.length; i++ ) {
            if( chars[i] == ' ') {
                reverse(left, i - 1);
                left = i + 1;
            }
        }
        reverse(left, chars.length - 1);
    }
    public void reverse(int start , int end) {
        while(start < end ) {
            swap(start++, end--);
        }
    }
    public void swap(int i , int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
​
```
