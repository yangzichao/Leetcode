# 937 Reorder-Data-in-Log-Files

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
<div><p>You have an array of <code>logs</code>.&nbsp; Each log is a space delimited string of words.</p>
<p>For each log, the first word in each log is an alphanumeric <em>identifier</em>.&nbsp; Then, either:</p>
<ul>
	<li>Each word after the identifier will consist only of lowercase letters, or;</li>
	<li>Each word after the identifier will consist only of digits.</li>
</ul>
<p>We will call these two varieties of logs <em>letter-logs</em> and <em>digit-logs</em>.&nbsp; It is guaranteed that each log has at least one word after its identifier.</p>
<p>Reorder the logs so that all of the letter-logs come before any digit-log.&nbsp; The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.&nbsp; The digit-logs should be put in their original order.</p>
<p>Return the final order of the logs.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
<strong>Output:</strong> ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ol>
	<li><code>0 &lt;= logs.length &lt;= 100</code></li>
	<li><code>3 &lt;= logs[i].length &lt;= 100</code></li>
	<li><code>logs[i]</code> is guaranteed to have an identifier, and a word after the identifier.</li>
</ol>
</div></section>

## Method One

```Java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a,b) -> {
            // 用空白分隔符给分成两段，第一段是identifier, 第二段是log内容
            String[] splitA = a.split(" ",2);
            String[] splitB = b.split(" ",2);
            
            //题目的描述不清楚。它的排序规则是，
            // 首先我们比较 log 的内容，letter 排在 digit 之前。
            // 都是digit 那么不排序，都是 letter 按 lexicographical 排序。
            // letter一样再给 identifier 按 lexicalgraphcial 排序
            String idenA = splitA[0];
            String idenB = splitB[0];
            String logA = splitA[1];
            String logB = splitB[1];
            boolean isADigit = Character.isDigit(logA.charAt(0));
            boolean isBDigit = Character.isDigit(logB.charAt(0));
            if(isADigit && isBDigit){
                return 0;
            }
            if(!isADigit && !isBDigit){
                int cmp = logA.compareTo(logB);
                if( cmp != 0){
                    return cmp;
                }
                return idenA.compareTo(idenB);
            }
            return isADigit ? 1 : -1;
        } );
        return logs;
    }
}​
```
