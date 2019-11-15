# 137J. Single Number II

https://leetcode.com/problems/single-number-ii/


这个题和137一起学习位运算

## 位运算法最佳
但是其实没必要看
有一些基本知识就可以了。


## 136 137 260 通用方法 HashMap 不错的

```Java
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> myMap = new HashMap<>();

        for(int n : nums){
            if(myMap.containsKey(n)){
                int temp = myMap.get(n) + 1;
                myMap.put(n,temp);
            }else{
                myMap.put(n,1);
            }
        }

        for(int n:nums){
            if(myMap.get(n) == 1){
                return n;
            }
        }
        return 0;
    }
}
```
