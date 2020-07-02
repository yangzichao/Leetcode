# 068 Text-Justification 
 
difficulty: Hard 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given an array of words and a width&nbsp;<em>maxWidth</em>, format the text such that each line has exactly <em>maxWidth</em> characters and is fully (left and right) justified.</p>
<p>You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces <code>' '</code> when necessary so that each line has exactly <em>maxWidth</em> characters.</p>
<p>Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.</p>
<p>For the last line of text, it should be left justified and no <strong>extra</strong> space is inserted between words.</p>
<p><strong>Note:</strong></p>
<ul>
	<li>A word is defined as a character sequence consisting&nbsp;of non-space characters only.</li>
	<li>Each word's length is&nbsp;guaranteed to be greater than 0 and not exceed <em>maxWidth</em>.</li>
	<li>The input array <code>words</code>&nbsp;contains at least one word.</li>
</ul>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong>
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
<strong>Output:</strong>
[
&nbsp; &nbsp;"This &nbsp; &nbsp;is &nbsp; &nbsp;an",
&nbsp; &nbsp;"example &nbsp;of text",
&nbsp; &nbsp;"justification. &nbsp;"
]
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong>
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
<strong>Output:</strong>
[
&nbsp; "What &nbsp; must &nbsp; be",
&nbsp; "acknowledgment &nbsp;",
&nbsp; "shall be &nbsp; &nbsp; &nbsp; &nbsp;"
]
<strong>Explanation:</strong> Note that the last line is "shall be    " instead of "shall     be",
&nbsp;            because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong>
words = ["Science","is","what","we","understand","well","enough","to","explain",
&nbsp;        "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
<strong>Output:</strong>
[
&nbsp; "Science &nbsp;is &nbsp;what we",
  "understand &nbsp; &nbsp; &nbsp;well",
&nbsp; "enough to explain to",
&nbsp; "a &nbsp;computer. &nbsp;Art is",
&nbsp; "everything &nbsp;else &nbsp;we",
&nbsp; "do &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
]
</pre>
</div></section>
 
 ## Method One 
 
``` Java

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        
        int curLength = 0;
        List<String> tempString = new ArrayList<>(); 
        
        for(int i = 0; i < words.length; i++){
            curLength += words[i].length();
            if(curLength > maxWidth) {
                ans.add( listToString(tempString, maxWidth, false) );
                tempString = new ArrayList<>();
                tempString.add(words[i]);
                curLength = words[i].length();
            }else{
                tempString.add(words[i]);
            }
            curLength += 1;
        }
        
        ans.add( listToString(tempString, maxWidth, true));
        return ans;
    }
    
    public String listToString (List<String> strings, int width , boolean isLastLine){
        String ans = "";
        if(strings.size() == 1){
            ans += strings.get(0);
            for(int i = width - ans.length(); i > 0; i--){
                ans += " ";
            } 
            return ans;
        }
        
        int length = 0;
        for(String str : strings){
            length += str.length();
        }
        int numberOfWhiteSpaces = width - length;
        int numberOfBaseSpaces = numberOfWhiteSpaces/(strings.size() - 1);
        int numberOfStringsWithExtraSpace = numberOfWhiteSpaces%(strings.size() - 1);
        int numberOfStringsWithBaseSpace = (strings.size() - 1);
        
        if(isLastLine) {
            for(String str: strings){
                ans += str + (numberOfStringsWithBaseSpace > 0 ? " ": "");
                numberOfStringsWithBaseSpace  -= 1;
            }
            for(int i = width - ans.length(); i > 0; i--){
                ans += " ";
            }   
            return ans;
        }
​
        
        String baseSpaces = "";
        for(int i = 0; i < numberOfBaseSpaces; i++ ) {
            baseSpaces += " ";
        }
        for(String str: strings) {
            ans += str + (numberOfStringsWithBaseSpace > 0 ? baseSpaces: "");
            ans += numberOfStringsWithExtraSpace > 0 ? " " : "";
            numberOfStringsWithBaseSpace  -= 1;
            numberOfStringsWithExtraSpace -= 1;
        }
        return ans;
    }
    
}
​
```