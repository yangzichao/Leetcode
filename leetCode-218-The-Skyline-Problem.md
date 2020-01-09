# 218J. The Skyline Problem
https://leetcode.com/problems/the-skyline-problem/

##
重点部分：
1. 这个题一个最重要的观察是：keypoints按什么规则记录？
   是每当当前的最高高度发生变化的时候(上升或者下降)，记录keypoints.
2. 最重要的edge case: 有两个长方形的一边在同一个位置时。

一般思路：
1. 把每个长方形视为两个竖线。一个左竖线，一个右竖线。建立竖线数组[pos,height]。
2. 区分左右竖线的办法是，把高度分为正负。左竖线用负高度。
3. 把所有的竖线加入到竖线数组中进行排序。优先按竖线的位置排序，位置相同时，**右竖线应当靠前**。
4. 为什么是右竖线靠前呢？这是为了强行给两个无缝衔接的方块制造交叉。否则的话代码会将他们
   识别为两个独立的方块，从而记录下错误的keypoints.
5. 我们从左到右扫描这些竖线。注意我们需要记录当前的最高高度。这样才能观察到最高高度的变化。
   同时我们还需要一个机制，使得我们遇到右竖线的时候能退出它对应的左竖线，并更新最大高度。
6. 符合这个需求的数据结构，自然就有heap,和TreeMap.
7. 我们先用heap.左竖线无脑入堆，堆顶就是当下的 max高度.
   当遇到右竖线的时候，从堆中删除右竖线对应的左竖线。
8. 如果堆顶的高度，和上一次循环的最大高度不一样，说明最大高度被更新了，
   我们应当记录key point 并 更新上一次的最大高度。

```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        
        List<int[]> heights = new ArrayList<>();
        for(int[] rectangle : buildings){
            int h   = rectangle[2];
            heights.add(new int[]{rectangle[0], - h});
            heights.add(new int[]{rectangle[1],   h});
        }
        heights.sort( (a,b) ->{
            if( a[0]!=b[0] ){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        List<List<Integer>> kps = new ArrayList<>(); // key points
        
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> b - a);
        heap.offer(0);
        int lastMax = 0;
        for(int[] height : heights){
            if(height[1] < 0){
                heap.offer(-height[1]);
            }else{
                heap.remove(height[1]);
            }
            int curMax = heap.peek();
            if(curMax != lastMax){
                List<Integer> temp = new ArrayList<>();
                temp.add(height[0]);
                temp.add(curMax);
                kps.add(temp);
                lastMax = curMax;
            }
        }
        return kps;
    }
}
```

可以想想用TreeMap写，而且更快。只是更难处理edge case了。
PQ:
135 ms	47.6 MB
TreeMap:
19 ms	42.6 MB

```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        
        List<int[]> heights = new ArrayList<>();
        for(int[] rectangle : buildings){
            int h   = rectangle[2];
            heights.add(new int[]{rectangle[0], - h});
            heights.add(new int[]{rectangle[1],   h});
        }
        heights.sort( (a,b) ->{
            if( a[0]!=b[0] ){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        
        List<List<Integer>> kps = new ArrayList<>(); // key points
        
        TreeMap<Integer, Integer> map = new TreeMap<>((a,b) -> b - a);
        map.put(0,0);
        int lastMax = 0;
        for(int[] height : heights){
            if(height[1] < 0){
                map.put(-height[1], map.getOrDefault(-height[1],0) + 1 );
            }else{
                int number = map.getOrDefault(height[1],0);
                if(number > 1){
                    map.put(height[1],  number - 1 ); 
                }else{
                    map.remove(height[1]);
                }
            }
            
            int curMax = map.firstKey();
            if(curMax != lastMax){
                List<Integer> temp = new ArrayList<>();
                temp.add(height[0]);
                temp.add(curMax);
                kps.add(temp);
                lastMax = curMax;
            }
        }
        return kps;
    }
}
```