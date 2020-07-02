# 273 Integer-to-English-Words

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
<div><p>Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2<sup>31</sup> - 1.</p>
<p><b>Example 1:</b></p>
<pre><b>Input:</b> 123
<b>Output:</b> "One Hundred Twenty Three"
</pre>
<p><b>Example 2:</b></p>
<pre><b>Input:</b> 12345
<b>Output:</b> "Twelve Thousand Three Hundred Forty Five"</pre>
<p><b>Example 3:</b></p>
<pre><b>Input:</b> 1234567
<b>Output:</b> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
</pre>
<p><b>Example 4:</b></p>
<pre><b>Input:</b> 1234567891
<b>Output:</b> "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        
        int billions = num/1000000000;
        int millions = (num%1000000000)/1000000;
        int thousands = ((num%1000000000)%1000000)/1000;
        int rest = ((num%1000000000)%1000000)%1000;
        
        String result = "";
        if(billions != 0){
            result += three(billions) + " Billion";
        }
        if(millions != 0){
            result += three(millions) + " Million";
        }
        if(thousands != 0){
            result += three(thousands) + " Thousand";
        }
        if( rest != 0) {
            result += three(rest);
        }
        return result.trim();
    }
    public String one(int n){
        switch(n){
            case 1: return " One";
            case 2: return " Two";
            case 3: return " Three";
            case 4: return " Four";
            case 5: return " Five";
            case 6: return " Six";
            case 7: return " Seven";
            case 8: return " Eight";
            case 9: return " Nine";
        }
        return "";
    }
    
    public String twoLessThan20(int n){
        switch(n){
            case 10: return " Ten";
            case 11: return " Eleven";
            case 12: return " Twelve";
            case 13: return " Thirteen";
            case 14: return " Fourteen";
            case 15: return " Fifteen";
            case 16: return " Sixteen";
            case 17: return " Seventeen";
            case 18: return " Eighteen";
            case 19: return " Nineteen";
        }
        return "";
    }
    
    public String two(int n){
        switch(n){
            case 20: return " Twenty";
            case 30: return " Thirty";
            case 40: return " Forty";
            case 50: return " Fifty";
            case 60: return " Sixty";
            case 70: return " Seventy";
            case 80: return " Eighty";
            case 90: return " Ninety";
        }
        return "";
    }
    
    public String three(int n){
        String ans = "";
        int hundreds = n /100;
        if(hundreds != 0){
            ans += one(hundreds) + " Hundred";
        }
        int tens = n%100;
        if(tens < 10){
            ans += one(tens);
        }else if( tens < 20){
            ans += twoLessThan20(tens);
        }else{
            ans += two(tens/10*10) + one(tens%10);
        }
        return ans;        
    }
}

```

```
