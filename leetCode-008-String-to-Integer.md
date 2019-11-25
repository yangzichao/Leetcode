# 008J. String to Integer (atoi)

https://leetcode.com/problems/string-to-integer-atoi/


## Method: 
```Java
class Solution {
    public int myAtoi(String str) {
        int rMax = Integer.MAX_VALUE%10;
        int rMin = Integer.MIN_VALUE%10;
        int tMax = Integer.MAX_VALUE/10;
        System.out.println(rMin);
        int ans = 0;
        boolean met = false;
        int sign = 1;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(met){
                if(Character.isDigit(c)){
                    int temp = (int) c - '0';
                    if(sign > 0 && ( ans < tMax || (ans == tMax && temp <= rMax)) ){
                        ans = ans*10 + temp;
                    }else if(sign < 0 && ( ans < tMax || (ans == tMax && temp <= -rMin))){
                        ans = ans*10 + temp;
                    }else{
                        return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }
                }else{
                    return sign*ans;
                }
            }else{
                if(c == ' ') {
                    continue;
                }else if(c == '+' || c == '-'){
                    if(i + 1 >= str.length()){
                        return 0;
                    }else if(Character.isDigit(str.charAt(i+1)) ){
                       sign = c == '+'? 1:-1;
                    }else{
                        return 0;
                    }   
                }else if(Character.isDigit(c)){
                    met = true;
                    ans += (int) c - '0';
                }else{
                    return 0;
                }
            }
        }
        return sign*ans;
    }
}
```
