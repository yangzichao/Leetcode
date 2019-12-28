# 621J. Task Scheduler



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