# 647 Palindromic-Substrings 
 
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
<div><p>Given a string, your task is to count how many palindromic substrings in this string.</p>
<p>The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> "abc"
<b>Output:</b> 3
<b>Explanation:</b> Three palindromic strings: "a", "b", "c".
</pre>
<p>&nbsp;</p>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> "aaa"
<b>Output:</b> 6
<b>Explanation:</b> Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
</pre>
<p>&nbsp;</p>
<p><b>Note:</b></p>
<ol>
	<li>The input string length won't exceed 1000.</li>
</ol>
<p>&nbsp;</p></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int countSubstrings(String s) {
        // 和 005 一样，没啥意思
        int total = 0;
        for(int i = 0; i < s.length(); i++ ) {
            total += expand(s, i, i);
            total += expand(s, i, i + 1);
        }
        return total;
    }
    public int expand(String s, int i, int j) {
        int count = 0;
        while( i >= 0 && j < s.length() ){
            if( s.charAt(i) == s.charAt(j) ) {
                i--;
                j++;
                count++;
            }else{
                return count;
            }
        }
        return count;
    }
}
​
```