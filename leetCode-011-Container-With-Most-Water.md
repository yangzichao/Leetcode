# 11. Container With Most Water
note:
Math.min(a,b),Math.max(a,b),可以返回更大更小的值。
## Method 1 : 暴力
```Java
class Solution {
    public int maxArea(int[] height) {
        int largest_volume = 0;
        for ( int i = 0; i < height.length; i++ ){
            for (int j = i + 1; j < height.length; j++ ) {
                int volume = 0;
                if (height[i] > height[j]){
                    volume = height[j] * (j - i);
                }else{
                    volume = height[i] * (j - i);
                }

                if (largest_volume < volume){
                    largest_volume = volume;
                }
            }
        }
        return largest_volume;
    }
}
```
## Method 2: （贪心法）
这个题通过理解题目，可以简化问题，降低算法难度。<br>
水桶的水取决于短板<br>
用两个指针标记最左，最右，其差值是长度。
* 我们试图遍历所有的长度。且必须遍历所有长度。（否则可能有遗漏）
* 每次令长度减一，令两个指针的一个向中间靠拢。每次应该移动的是高度更短的一侧，（否则移动毫无意义。若长度相等，则任意移动一侧即可。
* 每次移动应当比较面积。用另一个整型来记录最大面积。

note: 为何每次移动短侧？因为短侧决定了最大的储水量，移动长侧只能得到更少的储水量。移动短侧可以
期望得到更大的储水量。
```Java
class Solution {
    public int maxArea(int[] height) {
        int mostWater = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            mostWater = Math.max(mostWater, (right - left) *Math.min(height[left],height[right]));
            if(height[left ] > height[right]){
                right = right - 1;
            }else{
                left = left + 1;
            }

        }
        return mostWater;
    }
}
```
