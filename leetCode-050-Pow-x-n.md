# 050J. Pow(x, n)

https://leetcode.com/problems/powx-n/

暴力法失败了

## 暴力 1

```java
class Solution {
    public double myPow(double x, int n) {
        if(n < 0){
            x = 1/x;
            n *= -1;
        }
        return pow(x,n);
    }
    private double pow(double x, int n){
        if( n == 0) return 1.00;
        double half = pow(x,n/2);
        if( n %2 == 0){
            return half*half;
        }else{
            return half*half*x;
        }
    }
}
```

## Method 2

这个方法的核心就是 2^10 = 4^5 = 4*4^4 = 4^16^2 = 4 * 256 = 1024
或者 3^11 = 3*3^10 = 3*9^5 = 3*9*9^4 = 27*81^2

对于 java 我们只能用  long 来避免 int 的溢出问题，因为只有有符号的数字类型。
```java
class Solution {
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }
        long N = n; // 因为有 - 2.00000, -2147483648 这种奇葩test case 才加的这个 long 
        if(n < 0){
            x = 1/x;
            N = -N;
        }
        double power = x;
        double carry = 1;
        while( N > 1){
            if( (N % 2) != 0){
                carry *= power;
                N -= 1;
            }else{
                power *= power;
                N/=2;
            }
        }
        return power*carry;
    }
}
```

## Method Best 快速幂 fast powering or fast exponentiation

这是上面的代码优化的版本。

```java
class Solution {
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }
        long N = n; // 因为有 - 2.00000, -2147483648 这种奇葩test case 才加的这个 long 
        if(n < 0){
            x = 1/x;
            N = -N;
        }
        double res = 1;
        while( N > 0){
            if( (N & 1) != 0){
                res *= x;
                // N -= 1; 由于后面的原因，我们不用这一句代码就行
            }
            x *= x;  // 这里我们不用特别去算 res*x, 因为 N > 0 的条件，最后由于 N == 1 的时候一定会算一次 res *= x, 我们代码极大简化了。
            N >>= 1; // 对于奇数N, N >>= 1 相当于 (N - 1)/2, 其实也是 N/2了，可以细想一下。
        }
        return res;
    }
}
```
