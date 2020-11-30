# 049 Group-Anagrams

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
<div><p>Given an array of strings, group anagrams together.</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> <code>["eat", "tea", "tan", "ate", "nat", "bat"]</code>,
<strong>Output:</strong>
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]</pre>
<p><strong>Note:</strong></p>
<ul>
	<li>All inputs will be in lowercase.</li>
	<li>The order of your output does not&nbsp;matter.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<>();
        
        for(String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            ans.putIfAbsent(key, new ArrayList<String>());
            ans.get(key).add(str);
        }
        
        return new ArrayList(ans.values());
    }
}
​
```

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] count = new char[26];
            for(char c : str.toCharArray()) {
                count[c - 'a'] ++;
            }
            String key = String.valueOf(count);
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
```
