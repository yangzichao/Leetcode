# 008J. String to Integer (atoi)

https://leetcode.com/problems/string-to-integer-atoi/


## Method: Okay Method, O(N) somehow


经过测试，以下代码除非重写，基本没有小范围更改的余地。
```Java
class Solution {
    public int myAtoi(String str) {
        // 用来排除超出整型的部分
        int rMax = Integer.MAX_VALUE%10;
        int rMin = Integer.MIN_VALUE%10;
        int tMax = Integer.MAX_VALUE/10; // tMin和 tMax一样

        int ans = 0; //储存答案
        boolean met = false; //是否开始遇到整型
        int sign = 1; //记录正负号。
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(met){ // 如果已经判定遇到了数字，开始记录数字
                if(Character.isDigit(c)){ //如果本位仍然是数字，那么就进行判断，反之返回数字结果。
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
            }else{ //预处理遇到有效数字之前的情况。
                if(c == ' ') { // 空格跳过
                    continue;
                }else if(c == '+' || c == '-'){ // 遇到 + -
                    if(i + 1 >= str.length()){ // 如果没有下一位了 返回 0; 比如 “+” 这种输入。
                        return 0;
                    }else if(Character.isDigit(str.charAt(i+1)) ){ // 如果下一位是数字，则记录下来
                       sign = c == '+'? 1:-1; // 如果下一位是数字，记录下符号
                                              // 处理下一位的数字是下一次循环的事儿
                    }else{
                        return 0; //除此之外应当返回0
                    }   
                }else if(Character.isDigit(c)){ //如果是数字
                    met = true;   //说明直接遇到数字了 met = true;
                    ans += (int) c - '0'; // 记录下该数字
                }else{ //遇到数字之前的其他随机输入都是无效的 应当返回 0；
                    return 0;
                }
            }
        }
        return sign*ans;
    }
}
```
