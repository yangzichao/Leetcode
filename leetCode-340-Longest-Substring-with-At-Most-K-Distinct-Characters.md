# 340 Longest-Substring-with-At-Most-K-Distinct-Characters 
 
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
<div><p>Given a string, find the length of the longest substring T that contains at most <i>k</i> distinct characters.</p>
<p><strong>Example 1:</strong></p>
<div>
<pre><strong>Input: </strong>s = <span id="example-input-1-1">"eceba"</span>, k = <span id="example-input-1-2">2</span>
<strong>Output: </strong><span id="example-output-1">3</span>
<strong>Explanation: </strong>T is "ece" which its length is 3.</pre>
<div>
<p><strong>Example 2:</strong></p>
<pre><strong>Input: </strong>s = <span id="example-input-2-1">"aa"</span>, k = <span id="example-input-2-2">1</span>
<strong>Output: </strong>2
<strong>Explanation: </strong>T is "aa" which its length is 2.
</pre>
</div>
</div></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
​
        int size = 0;
        int max = 0;
        Map<Character, Integer> counts = new HashMap<>();
        
        char[] chars = s.toCharArray();
        int left = 0;
        
        for(int right = 0; right < s.length(); right++ ) {
            char c = chars[right];
            counts.put(c, counts.getOrDefault(c, 0) + 1);
            size += 1;
            while(counts.size() > k) {
                size -= 1;
                char last = chars[left++];
                counts.put(last, counts.get(last) - 1);
                if(counts.get(last) == 0) {
                    counts.remove(last);
                } 
            }
            max = Math.max(max, size);
        }
        return max;
    }
}
​
```