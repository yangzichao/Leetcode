# 621J. Task Scheduler


## Method 1 Best O(N) O(1);
对问题的观察: 为什么CPU会闲置，就是因为相同的任务的出现。如果就是 ABCDE，那么时间就是tasks长度。       
假如一个任务 AAA...A，k个A, 冷却时间 n = 2， 那么就得至少 （k - 1）* (n + 1)  + 1。   
因为前 k - 1 个占用 （k - 1）* (n + 1)  时长，然后得 + 1.          
如果有一大串任务 AAABBBCCCDDDEEEFFF.... , 首先我们知道，时间至少是 tasks 长度的。      
其次时间取决于  （k - 1）* (n + 1) 这个大框架能容下多少。     
如果这个大框架有空闲的，说明剩下的不足以填满，那么答案就是 （k - 1）* (n + 1) + p.     
p 是 k 次的任务数量。比如 k 个 A 和 k 个 B， p = 2.        
反之，如果它填满了。那么一定是 tasks 的长度是答案，这个确实很有意思，不知道怎么证明。

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        int maxCount = 0;
        int numberOfMax = 1;

        for(char c : tasks) {
            counts[c - 'A'] ++;
            if( counts[ c - 'A'] > maxCount ) {
                maxCount = counts[ c - 'A'];
                numberOfMax = 1;
            }else if ( counts[ c - 'A'] == maxCount ){
                numberOfMax++;
            }
        }
        
        return Math.max( tasks.length, (maxCount - 1)*(n + 1) + numberOfMax);
    }
}
```


## Mathod 2  Not best

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        
        //初始化一个 max heap
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);  
        int[] map = new int[26]; // 26 字母， 更多的用 hashmap
        for(char c : tasks){
            map[c - 'A']++;
        }
        for(int f: map){// f for frequency
            if( f > 0){
                heap.add(f);
            }
        }
        // 记录 time interval
        int time = 0;
        while(!heap.isEmpty()){
            int i = 0;// 记
            
            //顺序做任务，做n个就取出。
            List<Integer> temp = new ArrayList<>();
            //当不超过n个任务时
            while(i <= n){
                //如果有任务待做
                if(!heap.isEmpty()){
                    if( heap.peek() > 1 ){
                        //任务数量减1
                        temp.add(heap.poll() - 1);
                    }else{
                        //取出已经做完的任务
                        heap.poll();
                    }
                }
                //不管做没做任务，idle也要时间++
                time++;
                //如果队列都空了，并且temp的也做完了，直接结束 可以返回了
                if(heap.isEmpty() && temp.size() == 0){
                    return time;
                }
                //子时间指针++ 
                i++;
            }
            //把任务加回去。
            for(int l: temp){
                heap.add(l);
            }
        }
        return time;
    }
}
```