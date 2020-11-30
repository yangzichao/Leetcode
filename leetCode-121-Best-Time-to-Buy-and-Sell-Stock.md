# 121J. Best Time to Buy and Sell Stock

121 这个题不太容易直接想到最优解，最优解是这样，
只要我们的利润为正，我们就一直比较最大利润。  
一旦我们目前的利润为负了，我们就重新开始找这个原点。

```java
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int profit = 0;
        int left = 0;
        for(int i = 0; i < prices.length; i++) {
            profit = prices[i] - prices[left];
            max = Math.max(max, profit);
            if(profit < 0 ) {
                left = i;
            }
        }
        return max;
    }
}
```

121 这个题可以巧妙的转换成
[053: Maximum-Subarray](https://leetcode.com/problems/maximum-subarray/)
, 即计算每天和上一天的价格之差，那么
这个问题就是最大子数组和的问题了。

```java
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length < 1) {
            return 0;
        }
        int[] profitPerDay = new int[prices.length];
        for(int i = prices.length - 1; i > 0; i--) {
            profitPerDay[i] = prices[i] - prices[i-1];
        }
        int maxSum = profitPerDay[0];
        int curSum = profitPerDay[0];
        for(int i = 1; i < profitPerDay.length; i++) {
            curSum = ( curSum < 0 ?  0 : curSum) + profitPerDay[i];
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
```

根据上面的方法，把第一个方法写出了一个最新写法。
一个重要的观察是，某两天之间的利润，等于不停的+=相邻两天的利润差。

```java
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int curProfit = 0;
        for(int i = 1; i < prices.length; i++ ) {
            curProfit += prices[i] - prices[i - 1];
            max = Math.max( max, curProfit);
            if(curProfit < 0 ) {
                curProfit = 0;
            }
        }
        return max;
    }
}
```

ans = d0_coeff/2*(dn + dp) + d1_coeff/2*(dp - dn);
