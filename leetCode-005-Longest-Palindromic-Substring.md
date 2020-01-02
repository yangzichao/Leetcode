# 005J. Longest Palindromic Substring
https://leetcode.com/problems/longest-palindromic-substring/

## Method Not Bad: 
expand from corner.
从某一点开始，向两侧扩张。如果不一样就停止。回文字符串有两种情况，一奇数一偶数。
偶数的情况就从该点和下一个位置开始扩张。

这样的想法比较朴素，但是却能够达到 O(N^2)的时间复杂度，而且O(1)的空间复杂度。

```java
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1) return "";
        int ans = 0;
        int len = 0;
        int start = 0;
        int end = 0;
        
        for(int i = 0; i< s.length(); i++){
           len = Math.max(len,expander(s,i,i) );
           if(i < s.length() - 1){
              len = Math.max(len,expander(s,i,i+1) );  
           }
           if(len > ans){
               ans = len;
               start = i - (len - 1)/2;
               end =   i + len/2;
           }
            
        }
        return s.substring(start,end+1);
    }
    
    public int expander(String s, int left, int right){
        int length = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            length = (right - left) + 1;
            left--;
            right++;
        }
        return length;
    }
}
```


## Method Best: 马拉车算法

马拉车算法是对上面的算法的优化，但是执行起来过于复杂。
而且马拉车算法比较难以适用于其他的情况。
