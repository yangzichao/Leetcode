# 172J. Factorial Trailing Zeroes
https://leetcode.com/problems/factorial-trailing-zeroes/

## Method Best

```java
class Solution {
    public int trailingZeroes(int n) {
        /**
        由于2的数量管够，我们只关心能剥离出来多少个5.
        观察到 5 10 15 20 都能贡献一个5，从而贡献一个0
        而 25 能贡献 5*5,两个 5,从而贡献两个0.
        即阶乘到25之前，我们可以说 n/5，就是0的个数。
        而到了25之后， n/5，还要加上 n/5/5。
        25的倍数都能贡献两个0，比如50，75，100。
        而到了125,则能贡献3个0了。
        以此类推
        */
        
        
        return n < 5 ? 0 : n/5 + trailingZeroes(n/5);
    }
}
```