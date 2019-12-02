# 394J. Decode String

https://leetcode.com/problems/decode-string/

## Method: 最佳
用双栈是很正常的。
需要好好想想这个题。

```Java
class Solution {
    public String decodeString(String s) {
        Stack<Integer> stackI = new Stack<>();
        Stack<String> stackS = new Stack<>();
        int i = 0;

        String temp = ""; // temp 即是 temp 也是 最后的ans
        while(i < s.length() ){
            char c = s.charAt(i);
            if(Character.isDigit(c)){ //如果是数字的话 得到这个整数
                int num = 0;
                while(Character.isDigit(s.charAt(i))){
                    num = num*10 + (int) (s.charAt(i) - '0');
                    i++;
                }
                stackI.push(num);
            }else if( c == '['){
                // 这里我们遇到  '['，就我们知道要进入到下一层了
                // 由于下一层里需要处理重复的String，所以先在String栈里暂存一下我们现有的String
                //等过一会儿遇到 ']'了再取出来拼出正确的String。
                stackS.push(temp);
                temp = ""; //暂存完毕之后，暂时清空我们的temp
                i++;
            }else if(c == ']'){
                int times = stackI.pop(); //取出我们重复的次数
                String last = stackS.pop();//取出我们之前暂存的String
                //重建我们的新String。
                while(times > 0){
                    last += temp;
                    times--;
                }
                //拿到了正确的String之后出了这级括号
                temp = last;
                i++;
            }else{
                temp += String.valueOf(c);
                i++;
            }
        }
        return temp;
    }
}
```
