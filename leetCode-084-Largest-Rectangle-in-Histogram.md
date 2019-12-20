# 084J?. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/

请写一下不是用栈的方法。谢谢

## Method Best: Stack
<pre>
这个是怎么想的呢？其实还真不太好说。

具体的算法是建立一个栈储存id,也就是坐标pos.
如果栈空就入栈下一个，如果下一个将要入栈的比栈顶高，就入栈，反之就准备出栈。
出栈的时候就可以开始建立

</pre>
```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        int p = 0; // pointer from 0 to end;
        while(p < heights.length){
            if(stack.isEmpty()){
                stack.push(p);
                p++;
            }else{
                int curTop = heights[stack.peek()];
                if(curTop <= heights[p]){
                    stack.push(p);
                    p++;
                }else{
                    int posCurr = stack.pop();
                    int posLeft = stack.isEmpty() ? -1 :stack.peek();
                    int posRight = p;
                    int height = heights[posCurr];
                    int width = posRight - posLeft - 1;
                    int area = height * width;
                    ans = Math.max(area,ans);
                }
            }
        }
        // 这是为了解决结束之后栈还没有空的情形。
        while(!stack.isEmpty()){
            int posCurr = stack.pop();
            int posLeft = stack.isEmpty() ? -1 :stack.peek();
            int posRight = p;
            int height = heights[posCurr];
            int width = posRight - posLeft - 1;
            int area = height * width;
            ans = Math.max(area,ans);         
        }
        return ans;
        
    }
}
```
