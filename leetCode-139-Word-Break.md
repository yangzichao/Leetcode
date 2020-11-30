# 139 Word-Break

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
<div><p>Given a <strong>non-empty</strong> string <em>s</em> and a dictionary <em>wordDict</em> containing a list of <strong>non-empty</strong> words, determine if <em>s</em> can be segmented into a space-separated sequence of one or more dictionary words.</p>
<p><strong>Note:</strong></p>
<ul>
	<li>The same word in the dictionary may be reused multiple times in the segmentation.</li>
	<li>You may assume the dictionary does not contain duplicate words.</li>
</ul>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "leetcode", wordDict = ["leet", "code"]
<strong>Output:</strong> true
<strong>Explanation:</strong> Return true because <code>"leetcode"</code> can be segmented as <code>"leet code"</code>.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "applepenapple", wordDict = ["apple", "pen"]
<strong>Output:</strong> true
<strong>Explanation:</strong> Return true because <code>"</code>applepenapple<code>"</code> can be segmented as <code>"</code>apple pen apple<code>"</code>.
&nbsp;            Note that you are allowed to reuse a dictionary word.
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
<strong>Output:</strong> false
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private Set<String> set;
    private boolean[] marked;
    public boolean wordBreak(String s, List<String> wordDict) {
        this.set = new HashSet<>(wordDict);
        marked = new boolean[s.length() + 1];
        Queue<Integer> bfs = new LinkedList<>();
        bfs.offer(0);
        while( !bfs.isEmpty() ) {
            int size = bfs.size();
            while ( size > 0 ) {
                size -= 1;
                int cur = bfs.poll();
                for(int i = cur + 1; i <= s.length(); i++ ) {
                    if( marked[i] ) {
                        continue;
                    }
                    if(set.contains( s.substring(cur, i) ) ) {
                        if( i == s.length() ) {
                            return true;
                        }
                        bfs.offer(i);
                        marked[i] = true;
                    }
                }
            }
        }
        return false;
    }
    
}
​
```
