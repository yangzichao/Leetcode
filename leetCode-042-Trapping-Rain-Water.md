# 042J. Trapping Rain Water
https://leetcode.com/problems/trapping-rain-water/solution/

这个题如此重要，频率顺序第三，hard第一。非常值得一鸭三吃
参考了这个同学的一些方法，Credit是要给他的。
https://leetcode.wang/leetCode-42-Trapping-Rain-Water.html

## 暴力
<pre>
一般我们都自动忽略暴力法，对于这个题
理解暴力法在这里反而非常重要。
对于每一个位置 i ，分别找到它左侧和右侧最高的高度，如果两个
高度都比这个位置 i 高，说明这里可以储存下水，反之不行。
因此可以轻松的计算下这个位置 i 储存的水量。
全部加起来就可以了。

这个方法是标准的O(N^2), 空间O(1)
</pre>

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;

        int n  = height.length;
        for(int i = 1; i < n - 1; i++){
          //  找左侧最高
            int maxL = 0;
            for(int j = i - 1; j > -1; j--){
                if(height[j] > maxL){
                    maxL = height[j];
                }
            }
            // 找右侧最高
            int maxR = 0;
            for(int j = i+1; j < n; j++){
                if(height[j] > maxR){
                    maxR = height[j];
                }
            }
            //判断是否储存水
            if(height[i] < Math.min(maxL,maxR)){
                ans += Math.min(maxL,maxR) - height[i];
            }
        }
        return ans;
    }
}
```

## Method 2 : Dynamic Programming || 使用数组
<pre>
对于上面的暴力算法
我们只需要做一点点改进，就可以使我们的效率大大提高。
我们注意到，反复的扫描得到maxR 和 maxL 是效率很低的，
如果我们用两个数组分别保存所有对应位置的maxR 和 maxL,
我们只需要两次扫描，加上一次最终计算的扫描，就可以得到
答案了。

现在我们是 O(N), O(N)
上面暴力法我的提交是68毫秒。而改进之后已经是1毫秒了。
</pre>

```java
class Solution {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int ans = 0;
        int n = height.length;
        int[] maxL = new int[n];
        int[] maxR = new int[n];
        //maxL从左到右扫描，maxR 从右到左扫描，第一个位置需要初始化。
        maxL[0] = 0;
        maxR[n - 1] = 0;
        for(int i = 1; i < n; i++){
            maxL[i] = Math.max(height[i - 1],maxL[i - 1]);
        }
        for(int i = n - 2; i > - 1; i--){
            maxR[i] = Math.max(height[i + 1], maxR[i + 1]);
        }
        for(int i  = 1; i < n - 1; i++){
            ans += Math.min(maxL[i], maxR[i]) > height[i] ? Math.min(maxL[i], maxR[i]) - height[i] : 0;
        }
        return ans;
    }
}
```

## Method 3:
<pre>
上面这个方法我们发现，每一对maxL 和 maxR 我们用了就扔了。对空间比较浪费。
我们先想一个可以的优化，首先上面的三个for循环中，前两个可以减少至少一个。
具体减少哪一个取决于我们从左到右还是从右到左来计算水。
如果是从左到右，由于我们可以一直维护maxL, 所以这个数组是不需要的。具体看代码
</pre>

```java
class Solution {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int ans = 0;
        int n = height.length;

        int[] maxR = new int[n];
        maxR[n-1] = 0;
        for(int i = n - 2; i > -1; i--){
            maxR[i] = Math.max(maxR[i+1],height[i+1]);
        }

        int maxL = 0;
        for(int i = 1; i < n ; i++){
            // 核心就在下面这行代码。一直帮我们用一个指针记录了最大的右点。
            maxL = Math.max(maxL, height[i-1]);
            int temp = Math.min(maxL,maxR[i]);
            if(temp > height[i]){
                ans += temp - height[i];
            }
        }
        return ans;
    }
}
```
<pre>
有了上面的灵感，我们就得到了最好的方法。
上面的方法既可以从左到右递增指标i,也可以写成从右到左递减指标i。
每一种方法都只能减少一个for循环和一个数组。如果能结合这两个套路，岂不美哉？

所以最佳的方法是这样的，与其用顺序变化的指标 i, 不如灵活一点，
我们维护一对双指针，适合从左到右的时候就从左到右，适合从右到左的时候就从右到左。
如何判定这个条件呢？我们设 left 和 right 分别从 1 和 n - 2处 待命。我们判定
maxL 和 maxR 主要看的是 left - 1 和 right + 1。
      maxL = Math.max(height[left - 1],maxL)
      maxR = Math.max(height[right + 1], maxR);
如果 maxL 比 maxR 小，那么我们暂时没必要去动maxR，我们就一直动maxL这边的left，直到
maxL比maxR大了，我们再去移动right。这样当交汇的时候，就得到了所有的答案。
</pre>

```java
class Solution {
    public int trap(int[] height) {
        // if(height.length < 3) return 0;
        int ans = 0;
        int n = height.length;
        int maxL = 0;
        int maxR = 0;
        int left = 1;
        int right = n - 2;
        //判断语句也可以用下面这句，作用一样，总之就是让他们
        //运行 n - 2 次之后停止循环，因为我们只需要检查n - 2个位置。
        //for(int i = 1; i < n - 1; i++)
        while(left <= right) {
            maxL = Math.max(height[left - 1],maxL);
            maxR = Math.max(height[right + 1], maxR);
            if(maxL < maxR){
              //下面是一种省力的方法 省去了一个 if判断
                ans += Math.max(0,maxL - height[left]);
                left++;
            }else{
                ans += Math.max(0,maxR - height[right]);
                right--;
            }
        }
        return ans;
    }
}
```

## Method 4 : Stack
<pre>
用栈，我们存什么？存的是数组位置i。
我们要找什么？我们要找数组先降后升的特征，才能储存水。
因此数组不上升的话我们就入栈。如果下一个准备入栈的数增大了，
我们就需要准备处理出栈了。
我们的思路还是 maxL 和 maxR,
我们把当前的pos当作 maxR, 出栈一个当被考虑当位置，
然后看出栈之后当栈顶，直接把它当作maxL，这样就知道该处的深度了。
然后把宽度计算一下，就知道水的“面积”了。

</pre>

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        int pos = 0;
        while(pos < height.length){
          // 下面的while中 <= 也不影响结果，为什么？因为我们计算水的面积是一横条一横条计算的。
          // 所以不会影响结果 <= 对应的是 如果数组下降 我们就入栈，不下降就出栈。比如
          // 2，1，1，1，1，1，在栈中只会有 2，1，而忽略掉重复的对应的位置，这里的1是最后一个1。
    
            while(!stack.isEmpty() && height[stack.peek()] < height[pos] ){
                int maxR = height[pos];
                int h = height[stack.pop()];
                //栈空了就直接结束循环
                if(stack.isEmpty()) break;
                int maxL = height[stack.peek()];
                int distance = pos - stack.peek() - 1;
                int hight = Math.max(0,Math.min(maxR,maxL) - h);
                ans += distance * hight;
            }
            //如果不需要出栈 那么就入栈
            stack.push(pos);
            pos++;
        }
        return ans;
    }
}
```
