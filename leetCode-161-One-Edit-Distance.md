# 161 One-Edit-Distance 
 
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
<div><p>Given two strings <b><i>s</i></b>&nbsp;and <b><i>t</i></b>, determine if they are both one edit distance apart.</p>
<p><strong>Note:</strong>&nbsp;</p>
<p>There are 3 possiblities to satisify one edit distance apart:</p>
<ol>
	<li>Insert a&nbsp;character into <strong><em>s</em></strong>&nbsp;to get&nbsp;<strong><em>t</em></strong></li>
	<li>Delete a&nbsp;character from&nbsp;<strong><em>s</em></strong>&nbsp;to get&nbsp;<strong><em>t</em></strong></li>
	<li>Replace a character of&nbsp;<strong><em>s</em></strong>&nbsp;to get&nbsp;<strong><em>t</em></strong></li>
</ol>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> <strong><em>s</em></strong> = "ab", <strong><em>t</em></strong> = "acb"
<strong>Output:</strong> true
<strong>Explanation:</strong> We can insert 'c' into <strong><em>s</em></strong>&nbsp;to get&nbsp;<strong><em>t.</em></strong>
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> <strong><em>s</em></strong> = "cab", <strong><em>t</em></strong> = "ad"
<strong>Output:</strong> false
<strong>Explanation:</strong> We cannot get <strong><em>t </em></strong>from <strong><em>s </em></strong>by only one step.</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> <strong><em>s</em></strong> = "1203", <strong><em>t</em></strong> = "1213"
<strong>Output:</strong> true
<strong>Explanation:</strong> We can replace '0' with '1' to get&nbsp;<strong><em>t.</em></strong></pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        if( n < m ) {
            return isOneEditDistance(t, s);
        }
        // 设定 m <= n;
        // 如果长度差大于 1, 无解
        if( n - m > 1) {
            return false;
        }
        
        //至多有一个位置不一样。
        // 举例子, 两种情况，第一种长度相等。
        // abbb
        // acbb 
        // 如果某位不同，那么直接比较后面的子字符串
        // 第二种长度只差一位
        // abbb
        // acbbb
        // 如果某位不同，那么我们看看去掉长的字符串的这位之后，两个子数组是否还一致
        for(int i = 0; i < m; i++ ) {
            if ( s.charAt(i) == t.charAt(i) ) {
                continue;
            }
            if( n == m ) {
                return s.substring(i + 1, m).compareTo( t.substring( i + 1, n) ) == 0;
            }else{
                return s.substring(i, m).compareTo( t.substring(i + 1, n) ) == 0;
            }
        }
        return !(m == n);    
        
    }
}
​
```