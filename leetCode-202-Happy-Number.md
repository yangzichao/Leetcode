# 202J. Happy Number
https://leetcode.com/problems/happy-number/

## Method 1 HashSet TS O(logN) O(logN)
helper function nextHappy用来计算下一次。
用Set储存每一个新出现的值，如果出现了循环，那么就return false;
如果没有出现循环并且等于1，说明是happy number.
```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> mySet = new HashSet<Integer>();
        while(n!=1){
            mySet.add(n);
            n = nextHappy(n);
            if( mySet.contains(n) ){
                return false;
            }
        }
        return true;
    }
    public static int nextHappy(int n){
        int sum = 0;
        while(n > 0){
            int remainder = n%10;
            sum += remainder * remainder;
            n /= 10;
        }
        return sum;
    }
}
```
