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
