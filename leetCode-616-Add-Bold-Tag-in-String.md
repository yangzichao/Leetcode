# 616 Add-Bold-Tag-in-String

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
<div>Given a string <b>s</b> and a list of strings <b>dict</b>, you need to add a closed pair of bold tag <code>&lt;b&gt;</code> and <code>&lt;/b&gt;</code> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
<p><b>Example 1:</b></p>
<pre><b>Input:</b> 
s = "abcxyz123"
dict = ["abc","123"]
<b>Output:</b>
"&lt;b&gt;abc&lt;/b&gt;xyz&lt;b&gt;123&lt;/b&gt;"
</pre>
<p>&nbsp;</p>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
<b>Output:</b>
"&lt;b&gt;aaabbc&lt;/b&gt;c"
</pre>
<p>&nbsp;</p>
<p><b>Constraints:</b></p>
<ul>
	<li>The given dict won't contain duplicates, and its length won't exceed 100.</li>
	<li>All the strings in input have length in range [1, 1000].</li>
</ul>
<p><strong>Note:</strong> This question is the same as 758:&nbsp;<a href="https://leetcode.com/problems/bold-words-in-string/">https://leetcode.com/problems/bold-words-in-string/</a></p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public String addBoldTag(String s, String[] dict) {
        // 子字符串匹配的活不用我们自己来，用 s.startsWith();
        // 注意结尾收尾。
        boolean[] isBold = new boolean[s.length()];
        
        for(String word : dict) {
            for(int i = 0; i < s.length(); i++ ) {
                if( s.startsWith( word ,i) ) {
                    for(int j = 0; j < word.length(); j++ ) {
                        isBold[i + j] = true;
                    }
                }
            }
        }
        
        StringBuilder ans = new StringBuilder();
        boolean isBTagOpen = false;
        for(int i = 0; i < s.length(); i++ ) {
            if( isBold[i] && !isBTagOpen) {
                ans.append("<b>");
                isBTagOpen = true;
            }
            
            if(!isBold[i] && isBTagOpen) {
                ans.append("</b>");
                isBTagOpen = false;
            }
            ans.append(s.charAt(i));
        }
        
        if(isBTagOpen){
            ans.append("</b>");
        }
        return ans.toString();
    }
}
​
```
