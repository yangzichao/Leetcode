# 763 Partition-Labels 
 
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
<div><p>A string <code>S</code> of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.</p>
<p>&nbsp;</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> S = "ababcbacadefegdehijhklij"
<b>Output:</b> [9,7,8]
<b>Explanation:</b>
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
</pre>
<p>&nbsp;</p>
<p><b>Note:</b></p>
<ul>
	<li><code>S</code> will have length in range <code>[1, 500]</code>.</li>
	<li><code>S</code> will consist of lowercase English&nbsp;letters (<code>'a'</code> to <code>'z'</code>) only.</li>
</ul>
<p>&nbsp;</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        
        // Method 1 Greedy 核心是Two pointers, 
        // right 始终指向目前最右侧，left指向目前最左
        // 当 i 遇到 right 的时候，说明区间内的所有的字符的都被记录了。
        
        // int[] lastPositions = new int[26];
        // for(int i = 0; i < S.length(); i++){
        //     char c = S.charAt(i);
        //     lastPositions[c - 'a'] = i;
        // }
​
        // int right = 0;
        // int left = 0;
        // for(int i = 0; i < S.length(); i++){
        //     char c  = S.charAt(i);
        //     right = Math.max(right, lastPositions[c - 'a']);
        //     if( right == i ){
        //         ans.add( right - left + 1 );
        //         left = i + 1;
        //     }
        // }
        
        // Method 2 转化为 merge interval
        
        Map<Character, int[]> intervals = new HashMap<>();
​
        for(int i = 0; i < S.length(); i++){
            intervals.putIfAbsent(S.charAt(i), new int[]{i,i});
            intervals.get(S.charAt(i))[1] = i;
        }
        int curLeft = 0;
        int curRight = intervals.get(S.charAt(0))[1];
        for(int i = 1; i < S.length(); i++){
            char c  = S.charAt(i);
            int[] interval = intervals.get(c);
            if(interval[0] >= curRight){
                ans.add(curRight - curLeft + 1);
                curLeft = interval[0];
                curRight = interval[1];
            }else{
                curRight = Math.max(curRight, interval[1]);
                curLeft = Math.min(curLeft, interval[0]);
            }
        }
        ans.add(curRight - curLeft + 1);
        return ans;
    }
}
​
```