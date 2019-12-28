# 167J. Two Sum II
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

## Method Two Pointer
这个题核心的问题就是证明，为什么这么写是严谨的。
看下图，横实现是 target/2 的值，如果left,right 的平均值小于它，
显然应该提升left, 一直到平均值超过了再通过减小right,降低平均值。

为什么不会出现一种例外就是，例如我们图中right右侧的指针和图中left右侧的是答案呢？
如果是这样的话，我们的right就不会移动过来。所以这个是严谨的解法。
![167](imgs/LC167.png)

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] ans = new int[2];
        while(left < right){
            if(numbers[left] + numbers[right] > target){
                right--;
            }else if(numbers[left] + numbers[right] < target){
                left++;
            }else{
                ans[0] = left + 1;
                ans[1] = right + 1;
                return ans;
            }
        }
        return ans;
    }
}
```