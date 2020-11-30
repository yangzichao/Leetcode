# 006 ZigZag-Conversion 
 
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
<div><p>The string <code>"PAYPALISHIRING"</code> is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)</p>
<pre>P   A   H   N
A P L S I I G
Y   I   R
</pre>
<p>And then read line by line: <code>"PAHNAPLSIIGYIR"</code></p>
<p>Write the code that will take a string and make this conversion given a number of rows:</p>
<pre>string convert(string s, int numRows);
</pre>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "PAYPALISHIRING", numRows = 3
<strong>Output:</strong> "PAHNAPLSIIGYIR"
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "PAYPALISHIRING", numRows = 4
<strong>Output:</strong> "PINALSIGYAHRPI"
<strong>Explanation:</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>
<p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "A", numRows = 1
<strong>Output:</strong> "A"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>
<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of English letters (lower-case and upper-case), <code>','</code> and <code>'.'</code>.</li>
	<li><code>1 &lt;= numRows &lt;= 1000</code></li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public String convert(String s, int numRows) {
        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++){
            rows[i] = new StringBuilder();
        }
        // 这个做法就比较朴素。
        // 实际上有非常类似的写法。
        // 高明的地方就是用多个StringBuilder
        int curRow = 0;
        boolean isGoingDown = true;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            rows[curRow].append(c);
            if(curRow == numRows - 1){
                isGoingDown = false;
            }
            if(curRow == 0){
                isGoingDown = true;
            }
            curRow = isGoingDown ? curRow + 1 : curRow - 1;
            if(curRow < 0){
                curRow = 0;
            }
            if(curRow >= numRows){
                curRow = numRows - 1;
            }
        }
        for(int i = 1; i < numRows; i++){
            rows[0].append(rows[i].toString());
        }
        return rows[0].toString();
    }
}
​
```

## Method Two

```java
class Solution {
    public String convert(String s, int numRows) {
        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++){
            rows[i] = new StringBuilder();
        }
        int p = 0;
        // 这个方法看似爽，但是也很容易写错，一个稳妥的办法是，从上至下是完整的，而从下往上
        // 是从倒数第二行到第二行。 下面是错误的写法，虽然很对称，但是对 A,1 的输入TLE
        // while(p < s.length()){
        //     for(int i = 0; i < numRows - 1 && p < s.length(); i++){
        //         rows[i].append(s.charAt(p++));
        //     }
        //     for(int i = numRows - 1; i >= 1 && p < s.length(); i--){
        //         rows[i].append(s.charAt(p++));
        //     }
        // }
        while(p < s.length()){
            for(int i = 0; i < numRows && p < s.length(); i++){
                rows[i].append(s.charAt(p++));
            }
            for(int i = numRows - 2; i >= 1 && p < s.length(); i--){
                rows[i].append(s.charAt(p++));
            }
        }
        for(int i = 1; i < numRows; i++){
            rows[0].append(rows[i].toString());
        }
        return rows[0].toString();
    }
}
```