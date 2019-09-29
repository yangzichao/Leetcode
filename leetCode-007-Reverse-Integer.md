# 007. Reverse Integer


Given a 32-bit signed integer, reverse digits of an integer.

Example 1:  
Input: 123
Output: 321

Example 2:   
Input: -123
Output: -321

Example 3:   
Input: 120
Output: 21

Note:  
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

思路:

整数 x, 令除十的余数 x%10 = remain, 返回值 rev = 0
翻转它相当于:
* 不停的把旧的rev * 10,升一位，加上新的remain 得到新的 rev
* 然后用 x/10 给x降位，得到新的 x

但是要求不超出整型的范围，那么再得到最终的十位 rev之前，
要进行判断，是否 准备乘10的rev大于 2^31/10，是的话就返回0；
如果等于，就看准备加上的余数 是否大于7（正数） 或 8（负数），如果是就返回0；

## Method One:
基本的操作非常简单
```java
int rev = 0;
int remain = 0;
while(x != 0 ){
  remain = x % 10;
  rev = rev*10 + remain;
  x /= 10;
}
```
难点是如何判断是否要大于或者小于int型的边界呢？   
方法是在 rev * 10 + remain 之前判断。   
一个诀窍是可以用 Integer.MAX_VALUE 和 Integer.MIN_VALUE 得到最大和最小的整型值。

java 中 整型的最大最小值其语法如下

* Integer.MIN_VALUE
* Integer.MAX_VALUE
* -2147483648 = -2^31
* 2147483647 = 2^31 - 1

```java
class Solution {
    public int reverse(int x) {      
        int rev = 0;
        int remain = 0;
        while(x != 0){
            remain = x%10;
            if (rev > Integer.MAX_VALUE/10 ){return 0;}
            if (rev == Integer.MAX_VALUE &&  remain > Integer.MAX_VALUE%10 ){return 0;}
            if (rev < Integer.MIN_VALUE/10 ){return 0;}
            if (rev == Integer.MIN_VALUE &&  remain > Integer.MIN_VALUE%10 ){return 0;}
            rev = rev*10 + remain;
            x = x/10;
        }
        return rev;
    }
}
```
