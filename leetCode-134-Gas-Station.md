# 134J. Gas Station
https://leetcode.com/problems/gas-station/


## Method 1: Best

<pre>
第一遍是自己做的，虽然也没有特别复杂，但是还是啰嗦了，不得贪心法精要。
逻辑是，先把每站净消耗的gas算出来，如果为正，可以作为出发点，反之
则直接略过。然后对于每个为正的出发点进行测试。如果通过就return i；
</pre>

```Java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curGas = 0;
        for(int i = 0; i < gas.length; i++){
            gas[i] -= cost[i];
        }
        for(int i = 0; i < gas.length; i++){
            if(gas[i] >= 0){
                curGas = gas[i];
                int counter = 1;
                while(counter < gas.length){
                    curGas += gas[ (i + counter)%gas.length];
                    if(curGas < 0){
                        break;
                    }
                    counter++;
                }
                if(counter == gas.length){
                    return i;
                }
            }
        }
        return -1;
    }
}
```

<pre>
贪心法并不难。
首先globalGas只有大于等于0，才可能绕一圈。
curGas 储存的是当前净gas。if语句保证了
它一定从一个 gas[i] - cost[i]不为负的点开始。
并且后面如果遇到为负，就重新开始下一个判定。
</pre>

```Java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curGas = 0;
        int globalGas = 0;
        int startPos = 0;
        for(int i = 0; i < gas.length; i++){
            curGas += gas[i] - cost[i];
            globalGas += gas[i] - cost[i];
            if(curGas < 0){
                curGas = 0;
                startPos = i + 1;
            }
        }
        return globalGas < 0 ? -1:startPos;
    }
}
```
