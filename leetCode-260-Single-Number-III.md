# 260J. Single Number III
https://leetcode.com/problems/single-number-iii/

比起本系列前面两题，这个题不是很好。
强行位运算没有意义。

## Method Best: HashMap
意义就是学习 HashMap 一些接口
getOrDefault 还是很实用的
```Java
class Solution {
    public int[] singleNumber(int[] nums) {
      Map<Integer,Integer> myMap = new HashMap<>();
        for(int n : nums){
            myMap.put(n,myMap.getOrDefault(n,0) + 1);
        }
        int[] output = new int[2];
        int id = 0;
        for(int item: myMap.keySet()){
            if(myMap.get(item) == 1){
                output[id++] = item;
            }
        }
        return output;
    }
}
```
