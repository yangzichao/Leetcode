# 070J. Climbing Stairs
https://leetcode.com/problems/climbing-stairs/

## Method Fibonacci:
<pre>
这个题说白是一个斐波那契数列。但是不好直接想到。
这样想：
到第 i steps 有多少种方法呢？有两种，一个是从
i - 1 跨一步上来，一个是从 i - 2 跨两步上来。
所以总的步数就是 f(i - 1) + f(i - 2). 因此这就是一个
斐波那契数列。
</pre>

```java
class Solution {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        int minus1 = 2;
        int minus2 = 1;
        int cur = 2;
        for(int i = 3; i < n + 1; i++){
            cur = minus1 + minus2;
            minus2 = minus1;
            minus1 = cur;
        }
        return cur;
    }
}
```
