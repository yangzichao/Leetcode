# 151 Reverse-Words-in-a-String

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
<div><p>Given an input string, reverse the string word by word.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> "<code>the sky is blue</code>"
<strong>Output:&nbsp;</strong>"<code>blue is sky the</code>"
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> " &nbsp;hello world! &nbsp;"
<strong>Output:&nbsp;</strong>"world! hello"
<strong>Explanation:</strong> Your reversed string should not contain leading or trailing spaces.
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> "a good &nbsp; example"
<strong>Output:&nbsp;</strong>"example good a"
<strong>Explanation:</strong> You need to reduce multiple spaces between two words to a single space in the reversed string.
</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ul>
	<li>A word is defined as a sequence of non-space characters.</li>
	<li>Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.</li>
	<li>You need to reduce multiple spaces between two words to a single space in the reversed string.</li>
</ul>
<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>
<p>For C programmers, try to solve it <em>in-place</em> in <em>O</em>(1) extra space.</p></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public String reverseWords(String s) {
        String[] strings = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = strings.length - 1; i >= 0; i-- ) {
            if(strings[i].compareTo("") == 0 ) {
                continue;
            }
            sb.append( strings[i] );
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
​
```
