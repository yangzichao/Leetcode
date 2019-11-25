# 227J. Basic Calculator II
https://leetcode.com/problems/basic-calculator-ii/


## Method 几乎最佳了
解释基本都在代码中
原理是用一个整数Stack来保存运算结果
```Java
class Solution {
    public int calculate(String s) {

        Stack<Integer> stack = new Stack<>();
        // 以下两个初始化相当于在运算的头部加上了一个 dummyhead "+0";
        char sign = '+';//用来保存上一个sign
        int sum = 0;    // 用来保存数字

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                // 如果该字符是数字的话
                //这里是为了考虑连续几个字符都是数字的情况
                //可以得到正确的十进制数
                sum = sum*10 + (int) (c -'0');
            }
            // 这个判断条件是不能变的。
            // 前两个一起是选择了 + - * / 排除了 空格
            // 后面是因为循环到结尾的时候，还有一个运算没有做
            // 举例： " 3+ 2 " 会停止在 sum = 2, sign = '+'
            // 此时无论最后一位是 ' '还是 '2', 都可以保证强制算最后一次。
            if( (!Character.isDigit(c) && c != ' ' )|| i == s.length() - 1){
                // 注意以下判断条件都是 sign.equals(), 使用的是上个符号
                // 判断完上一个符号之后，由于现在这个符号也不是数字，所以
                // 首先应当对上一个符号进行的运算做清算。
                // 然后再把现在这个符号赋值给sign。
                if(sign == '+' ){
                    //如果上一个符号是 +， 上一个数字就是 +sum
                    stack.push(sum);
                }
                if(sign == '-' ){
                    //如果上一个符号是 -， 上一个数字就是 -sum
                    stack.push(-sum);
                }
                if(sign == '*' ){
                   //如果上一个符号是 * ， 上一个数字就是 sum
                    // 上上个就在 stack.pop() 里
                    stack.push(sum*stack.pop());
                }
                if(sign == '/' ){
                   //如果上一个符号是 / ， 上一个数字就是 sum
                    // 上上个(被除数)就在 stack.pop() 里  
                    stack.push(stack.pop()/sum);
                }
                //不要忘记更新sign;
                sign = c;
                //做了运算不要忘记令sum重回0
                sum = 0;
            }
        }


        //现在stack里面已经存满了一堆正负数了，求和即可。
        int ans = 0;
        for(int n : stack){
            ans += n;
        }
        return ans;
    }
}
```
