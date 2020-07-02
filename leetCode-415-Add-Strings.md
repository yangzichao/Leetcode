# 415 Add-Strings

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
<div><p>Given two non-negative integers <code>num1</code> and <code>num2</code> represented as string, return the sum of <code>num1</code> and <code>num2</code>.</p>
<p><b>Note:</b>
</p><ol>
<li>The length of both <code>num1</code> and <code>num2</code> is &lt; 5100.</li>
<li>Both <code>num1</code> and <code>num2</code> contains only digits <code>0-9</code>.</li>
<li>Both <code>num1</code> and <code>num2</code> does not contain any leading zero.</li>
<li>You <b>must not use any built-in BigInteger library</b> or <b>convert the inputs to integer</b> directly.</li>
</ol>
<p></p></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int p1 = chars1.length -1;
        int p2 = chars2.length -1;
        int carry = 0;
        while(p1 > -1 || p2 > -1){
            int n1 = p1 < 0 ? 0: chars1[p1] - '0';
            int n2 = p2 < 0 ? 0: chars2[p2] - '0';
            int sum = n1 + n2 + carry;
            carry = sum/10;
            ans.insert(0,sum%10);
            p1--;
            p2--;
        }
        if(carry > 0){
            ans.insert(0, 1);
        }
        return String.valueOf(ans);
    }
}
​
```
