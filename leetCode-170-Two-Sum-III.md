# 170J. Two Sum III

https://leetcode.com/problems/two-sum-iii-data-structure-design/

还是 Two-Sum 嘛还行。

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
