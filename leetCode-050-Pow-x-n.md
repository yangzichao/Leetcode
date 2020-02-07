# 050J. Pow(x, n)
https://leetcode.com/problems/powx-n/

暴力法失败了

## 暴力1

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
