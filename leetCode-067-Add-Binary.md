# 067J. Add Binary
https://leetcode.com/problems/add-binary/

## Method: Best
容易题，几乎秒杀
思路从后往前加，最后翻转一下StringBuilder.
```Java
class Solution {
    public String addBinary(String a, String b) {
        int pA = a.length() - 1;
        int pB = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while(pA >= 0 || pB >= 0){
            int numA = pA >= 0 ? (int) a.charAt(pA) - '0' : 0;
            int numB = pB >= 0 ? (int) b.charAt(pB) - '0' : 0;
            int temp = (numA + numB + carry)%2;
            carry = (numA + numB + carry)/2;
            sb.append(String.valueOf(temp));
            pA--;
            pB--;    
        }
        if(carry > 0) sb.append(String.valueOf(carry));
        return sb.reverse().toString();
    }
}
```

## Method Best: Bit Operation
