# 010 Regular-Expression-Matching 
 
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
<div><p>Given an input string (<code>s</code>) and a pattern (<code>p</code>), implement regular expression matching with support for <code>'.'</code> and <code>'*'</code>.</p>
<pre>'.' Matches any single character.
'*' Matches zero or more of the preceding element.
</pre>
<p>The matching should cover the <strong>entire</strong> input string (not partial).</p>
<p><strong>Note:</strong></p>
<ul>
	<li><code>s</code>&nbsp;could be empty and contains only lowercase letters <code>a-z</code>.</li>
	<li><code>p</code> could be empty and contains only lowercase letters <code>a-z</code>, and characters like&nbsp;<code>.</code>&nbsp;or&nbsp;<code>*</code>.</li>
</ul>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong>
s = "aa"
p = "a"
<strong>Output:</strong> false
<strong>Explanation:</strong> "a" does not match the entire string "aa".
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong>
s = "aa"
p = "a*"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;'*' means zero or more of the preceding&nbsp;element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong>
s = "ab"
p = ".*"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;".*" means "zero or more (*) of any character (.)".
</pre>
<p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong>
s = "aab"
p = "c*a*b"
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
</pre>
<p><strong>Example 5:</strong></p>
<pre><strong>Input:</strong>
s = "mississippi"
p = "mis*is*p*."
<strong>Output:</strong> false
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int j = 1; j < n; j += 2) {
            dp[0][j + 1] = p.charAt(j) == '*' && ( j > 1 ? dp[0][j - 1] : true );
        }

        
        for(int i = 0; i < m; i++ ) {
            for(int j = 0; j < n; j ++ ) {
                char sc = s.charAt(i);
                char pc = p.charAt(j);
                if( equals(sc, pc) ) {
                    dp[i + 1][j + 1] = dp[i][j];
                    continue;
                }
                if( pc != '*' ) {
                    continue;
                }
                if( j < 1 ) {
                    // 对应的 case 是 p = "*"
                    continue;
                }
                char pre = p.charAt( j - 1 );  
                if( !equals(sc, pre) ) {
                    dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    continue;
                }
                dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[ i ][ j + 1 ] || dp[i + 1][j]; 
            }
        }
        return dp[m][n];
    }
    public boolean equals(char s, char p) {
        return s == p || p == '.';
    }
}



/**
这个题只是 '.' 一点难度没有。对于两个字符的比较，
如果 a == b || b == '.' 我们都认为是 match.
我们写个 helper function 判定这种情况。
下面我们用 a === b 来表示 a == b || b == '.';

sc s[i - 1];
pc p[j - 1];
pre = p[j - 2];
result: dp[i][j];

if ( sc === pc ) {
    // 这种情况举例 s = aab, p = ab*
    // 我们判断子问题 s = aa, p = a，
    dp[i][j] = dp[i - 1][j - 1];
    continue;
}

如果 sc !== pc:
只需要考虑 pc == '*' 的情况，如果不是那么就不匹配

if (pc != '*' ) {
    d[i][j] = false;
    continue;
}

pc == '*', 由于'*' 会重复之前的字符，所以 pre =   p[j - 2]也要考虑。

此时有两种情况:
1 pre != sc, => '*' don't repeat, for example : s = ab, p = abc*

if( pre != sc ) {
    dp[i][j] = dp[i][j-2];
}

2 pre == sc,

if( p[j - 2] == s[i - 1] ) {
    1:  仍然可以选择重复0次，比如 s = ac, p = acc*;
    2:  重复一次 =>  '*' repeat one times for example : s = acb, p = acb*,子问题 ac,ac
    dp[i][j] = dp[i - 1][j - 2];
    3: 重复多次 => '*' repeat many times for example : s = baa, p = ba*, 子问题 ba ba*
    dp[i][j] = dp[i - 1][j];
}

1 和 2 和 3 的情况随便一种成立就行，所以 || 就好了
三种情况直接合并
dp[i][j] = dp[i][j-2] || dp[i - 1][j - 2] || dp[i - 1][j];


// 判断边界条件
空字符串互相匹配 dp[0][0] = true;
空pattern 不匹配任何 dp[i][0] = false; // i > 0
空字符串对非空 pattern, 只能靠 '*' 重复0次满足
比如 s ="", p = 'a' false; s ="", p = "a*",可以!
即 p 必须是形如 a*b*c*.*, 在 j = 1,3,5...都是*才行。
所以 dp[0][j] = d[0][j - 2] && p[j - 1] == '*';
*/

```