# 409J. Longest Palindrome

垃圾题不值一提哦

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] charfre = new int[128];
        for(char c : s.toCharArray()){
            charfre[c]++;
        }
        int ans = 0;
        int maxOdd = 0;
        for(int val: charfre){
            if(val%2 != 0){
                ans += (val - 1);
                maxOdd = Math.max(maxOdd, val);
            }else{
                ans += val;
            }
        }
        return ans + maxOdd%2;
    }
}
```
