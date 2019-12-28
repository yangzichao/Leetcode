# *862J Shortest Subarray with Sum at Least K
https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/

## Method Best 
题目亮点1: 单调队列，可以查看笔记相关内容。
题目亮点2: 因为是sum，所以利用“积分”直接记录下求和的内容，以免重复计算。
难点1: 应当使用 dummy 位置来计算长度差。
难点2: 想到使用单调队列，但是要注意我们的指针所指的位置的值，是不在计算范围内的。
看我们代码实现的部分。
比如第二个for中的i, i 对应的A中元素为非正值的时候，计算 I[j] - I[i] 之间的差值时，是不包含这个负值的。
所以要把大的踢掉。
```java
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length; //方便得到N 无特别意义
        // I for integral 积分，N + 1是将第一个当作dummyhead,默认为0
        // 为了较轻松的得到正确的长度， dummyhead 方便
        int[] I = new int[N + 1];
        // 初始化 I[]， 积分能够直接获取求和的信息，以免重复计算的浪费
        for(int i = 1; i < N + 1; i++){
            I[i] = I[i-1] + A[i - 1];
        }
        // 初始化答案变量 为一个不可能的值，如果是的话就返回 -1 
        int ans = N + 1;
        // 初始化 monotonic increasing queue, 按值排序，储存的是id,
        Deque<Integer> mq = new LinkedList<>();
        
        for(int i = 0; i < N + 1; i++){//遍历 I
            //新元素入列先要经受考验。
            //队列空的直接跳过判断入列
            //如果队列尾部的不如当前待定的值小，那么应当出列。
            //这是对应的情况 比如 [1，-1，9999] 找K = 2，
            // 我们取的长度自然是从 -1的 id 到9999的id之差 没有理由令 1 的 id 还在列中
            // 同理 如果有 0 的话也是毫无意义的 应当排除
            while(!mq.isEmpty()&& I[i] <= I[mq.getLast()]){
                mq.removeLast();
            }
            //若我们发现, 待入列元素和列首之差已经不小于K了，
            //我们就做正常的sliding window会做的事，先不急着入列当前元素，一会儿就入了。
            while(!mq.isEmpty()&& I[i] - I[mq.getFirst()] >= K){
                ans = Math.min(ans, i - mq.getFirst() );
                mq.removeFirst();
            }
            
            mq.addLast(i);
        }
        
        return ans < N + 1 ? ans : -1;
    }
}
```