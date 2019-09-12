# 733. Flood Fill
An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input:
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation:
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].

## 思路：
这个题是一个很好的2D 4方向的迭代问题
题目管这个解法叫：Depth-First Search （DFS）
需要注意的是:
* 执行迭代前要注意不要令下一步超出数组范围。所以有四个判断。
* floodFill方法里的 if(outcolor != newColor) 是必须的。
因为这个题判断是否是已经遍历过的点，就是靠的颜色不同。新旧颜色相同
会陷入无限循环。如果新旧颜色相同其实不需要执行就可以return了。





```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int outcolor = image[sr][sc];   
        if (outcolor != newColor) {dfs(image,sr,sc,outcolor,newColor);}
        return image;
    }

     public void dfs ( int[][] image, int r, int c, int color, int newColor){
         if (image[r][c] == color) {
             image[r][c] = newColor;
             if(r > 0) {
                 dfs(image, r-1, c, color, newColor);
             }
             if (c > 0){
                 dfs(image, r, c-1, color, newColor);
             }
             if (r < image.length - 1){
                 dfs(image, r + 1, c, color, newColor);
             }
             if (c < image[0].length - 1) {
                 dfs(image, r, c + 1, color, newColor);
             }
         }
     }
}
```
