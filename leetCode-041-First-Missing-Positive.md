# 041J. First Missing Positive
https://leetcode.com/problems/first-missing-positive/

相关题目
268 448

## Method Trivial:

如果能用空间 O(N)，你会怎么做？
首先想到可以用HashMap, 这个题一个更好的HashMap就是用数组。
直接类似桶排序即可。

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int N = nums.length;
        boolean[] fuck = new boolean[N+1];
        for(int i = 0; i < N; i++){
            if(nums[i] < 0 || nums[i] > N) {
                continue;
            }
            fuck[nums[i]] = true;
        }
        for(int i = 1; i < N + 1; i++){
            if(!fuck[i]){
                return i;
            }
        }
        return N + 1;
        
    }
}
```

## Method Best:

通过上面的代码，我们设想可以不新建数组，直接在原来的数组上进行同样的操作。
1. 因此首先需要预处理数组，把不符合要求的数（非正数和大于N的），都处理掉。
   令他们都是N+1,这样我们通过数值本身就区别了符合要求的和不符合的。
2. 将符合要求的数直接在原地进行方法1中的排序。如何将原位的数组剥离成两个独立
   的数组呢？答案是利用正负号。
   比如 nums[2] = 3, nums[3] = 2.
   nums[3] = -2, 负号意味着 idx 3 已经有了，当我们运行到 idx = 3时，
   我们取绝对值2, 这是进入到key中。然后找到 num[2] 标记为 -3。这个负号说明
   被标记过了。
3. 由于我们没有 N + 1 长度的数组，只好把各个位置都移一位到 i - 1。

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int N = nums.length;
        for(int i = 0; i < N; i++){
            if(nums[i] <= 0 || nums[i] > N) {
                nums[i] = N + 1;
            }
        }
        for(int i = 0; i < N; i++){
            int num = Math.abs(nums[i]); 
            if(num > N ) continue;
            num --;
            if( nums[num] > 0){ // 这是为了防止有重复的数 导致负负得正
                nums[num] *= -1;
            }    
        }
        
        for(int i = 0; i < N; i++){
            if(nums[i] >= 0){
                return i + 1;
            }
        }
        // 如果全部都被标记过，那么就返回 N + 1.
        return N + 1;
    }
}
```