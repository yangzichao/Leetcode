# 242 Valid-Anagram

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
<div><p>Given two strings <em>s</em> and <em>t&nbsp;</em>, write a function to determine if <em>t</em> is an anagram of <em>s</em>.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> <em>s</em> = "anagram", <em>t</em> = "nagaram"
<b>Output:</b> true
</pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> <em>s</em> = "rat", <em>t</em> = "car"
<b>Output: </b>false
</pre>
<p><strong>Note:</strong><br>
You may assume the string contains only lowercase alphabets.</p>
<p><strong>Follow up:</strong><br>
What if the inputs contain unicode characters? How would you adapt your solution to such case?</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] map = new int[26];
        for(char c : s.toCharArray()) {
            map[ c - 'a' ]++;
        }
        for(char c : t.toCharArray()) {
            map[ c - 'a' ]--;
        }
        for(int n : map) {
            if(n != 0) {
                return false;
            }
        }
        return true;
    }
}
​
```
