# 066J. Plus One

https://leetcode.com/problems/plus-one/


## 方法1
优点是可以处理非加1的情况。缺点是代码比较啰嗦。
原理是从后往前处理，即从个位起处理。
原则就是在判断是否需要进位。
```Java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        digits[n-1]+=1;
        int carry = 0;
        for(int i = n - 1; i >= 0; i--){
            if( digits[i] + carry > 9){
                digits[i] = 0;
                carry = 1;
            }else{
                digits[i]+=carry;
                carry = 0;
                return digits;
            }
        }

        int[] newN = new int[n+1];
        newN[0] = 1;
        return newN;
    }
}
```
## Method2
这个方法代码优雅一些。

```Java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for(int i = n-1; i>=0;i--){
          //先判断是否小于9，如果小于9。就无进位的考虑。
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
          // 如果需要进位，就把这一位设为0；下一个循环再考虑是否需要进位
            digits[i] = 0;

        }
        int[] newNumber = new int[n+1];
        newNumber[0] = 1;
        return newNumber;
    }
}
```
