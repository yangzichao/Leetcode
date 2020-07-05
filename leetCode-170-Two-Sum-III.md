# 170J. Two Sum III

https://leetcode.com/problems/two-sum-iii-data-structure-design/

还是 Two-Sum 嘛还行。

但是这个解法不是最优解

```java
class TwoSum {
    private List<Integer> nums;

    /** Initialize your data structure here. */
    public TwoSum() {
        this.nums = new ArrayList<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.nums.add(number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        Set<Integer> set = new HashSet<>();
        for(int n : this.nums){
            if(set.contains(value - n)){
                return true;
            }
            set.add(n);
        }
        return false;
    }
}
```

```java

class TwoSum {

    private Map<Integer, Integer> map;
    // 最优解明显是使用一个 HashMap, 用来记录每个值的数量

    /** Initialize your data structure here. */
    public TwoSum() {
        this.map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.map.put(number,  this.map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int n : this.map.keySet() ) {
            int target = value - n;
            if(target == n) {
                if(this.map.getOrDefault(target, 0) > 1) {
                    return true;
                }
            }else{
                if(this.map.getOrDefault(target, 0) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```
