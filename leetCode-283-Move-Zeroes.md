# 283J. Move Zeroes
https://leetcode.com/problems/move-zeroes/


## 最优解

本题和 27 思路一致
https://leetcode.com/problems/remove-element/
<pre>
这是愚蠢版本的代码，但是想法也没错。
用快慢指针，快指针永远指向下一个非0的数，然后慢指针就把这个数取得，
然后挪一次慢指针。
while 内的 while 是用来移动快指针到下一个非0的值。判断语句
是用来防止末尾是0从而令指针超出范围。
优化：可以用for来避免这个。
后面的temp来交换也没必要,第一遍可以不管是否有0，只需要while完之后
将slow 之后的都赋值为0即可，比较简便。

</pre>
```Java
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        int temp;
        while(fast < nums.length){
            while(nums[fast] == 0){
                if(fast < nums.length - 1){
                    fast++;
                }else{
                    break;
                }
            }
            temp = nums[fast];
            if(nums[slow] == 0){
                nums[fast] = 0;
            }
            nums[slow] = temp;
            slow++;
            fast++;
        }
    }
}
```

## 确实练习是有进步的
新方法，自动最优了。
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;
        for(int i = 0; i < nums.length; i++){
            if( nums[i] != 0 ){
                swap(nums, i, left);
                left++;
            }
        }
    }
    public void swap(int[] nums, int i, int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```