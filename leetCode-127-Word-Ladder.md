# 127 Word-Ladder 
 
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
<div><p>Given two words (<em>beginWord</em> and <em>endWord</em>), and a dictionary's word list, find the length of shortest transformation sequence from <em>beginWord</em> to <em>endWord</em>, such that:</p>
<ol>
	<li>Only one letter can be changed at a time.</li>
	<li>Each transformed word must exist in the word list.</li>
</ol>
<p><strong>Note:</strong></p>
<ul>
	<li>Return 0 if there is no such transformation sequence.</li>
	<li>All words have the same length.</li>
	<li>All words contain only lowercase alphabetic characters.</li>
	<li>You may assume no duplicates in the word list.</li>
	<li>You may assume <em>beginWord</em> and <em>endWord</em> are non-empty and are not the same.</li>
</ul>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong>
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
<strong>Output: </strong>5
<strong>Explanation:</strong> As one shortest transformation is "hit" -&gt; "hot" -&gt; "dot" -&gt; "dog" -&gt; "cog",
return its length 5.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong>
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
<strong>Output:</strong>&nbsp;0
<strong>Explanation:</strong>&nbsp;The endWord "cog" is not in wordList, therefore no possible<strong>&nbsp;</strong>transformation.
</pre>
<ul>
</ul>
</div></section>
 
 ## Method One 
 这是我独立写的，只是单侧 bfs. 
``` Java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(  !dict.contains(endWord)  ) {
            return 0;
        }
        dict.add(beginWord);
        Map<String, Set<String>> map = new HashMap<>();
        for(String word : dict) {
            map.putIfAbsent(word, new HashSet<String>() );
            for(String next : wordList) {
                if( next == word || !isValidNeighbor(word, next) ) {
                    continue;
                }
                map.putIfAbsent(next, new HashSet<String>() );
                map.get(word).add(next);
                map.get(next).add(word);
            }
        }
        
        Set<String> used = new HashSet<>();
        Queue<String> bfs = new LinkedList<>();
        bfs.offer(beginWord);
        int steps = 0;
        while( !bfs.isEmpty()) {
            int size = bfs.size();
            steps += 1;
            while( size > 0 ) {
                size--;
                String cur = bfs.poll();
                used.add(cur);
                if( cur.compareTo(endWord) == 0 ) {
                    return steps;
                }
                for( String next : map.get(cur) ) {
                    if( used.contains(next) ) {
                        continue;
                    }
                    bfs.offer(next);
                }
            }
        }
        return 0;
        
    }
    
    public boolean isValidNeighbor (String s1, String s2 ) {
        if( s1.length() != s2.length() ) {
            return false;
        }
        int diffCount = 0;
        for(int i = 0; i < s1.length(); i++ ) {
            if( s1.charAt(i) != s2.charAt(i) ) {
                diffCount++;
                if(diffCount > 1) {
                    return false;
                }
            }
        }
        return diffCount == 1;
    }
}
​
```