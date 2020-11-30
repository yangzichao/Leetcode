# 347J. Top K Frequent Elements

https://leetcode.com/problems/top-k-frequent-elements/

## Method Trivial: PQ

随着做题越来越多，信手拈来。
这个方法是
N Log k 的 Time Complexity.

```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int i : nums){
            frequency.put(i, frequency.getOrDefault(i,0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1, (key1, key2) ->
                        frequency.get(key1) - frequency.get(key2) );
        for(int key : frequency.keySet()){
            heap.offer(key);
            if(heap.size() > k){
                heap.poll();
            }
        }
        List<Integer> topK = new LinkedList<>();

        while(!heap.isEmpty()){
            topK.add(heap.poll());
        }
        Collections.reverse(topK);
        return topK;

    }
}
```

## Method Best: Bucket Sort

1. 先试用 Hash 表统计字符串中每个字符出现的次数；
2. 然后使用数组构建 N 个“桶”，数组的下标对应字符在字符串出现的次数，每个桶里面存储的是字符的集合，这样的话，出现次数相等的字符则被放入了同一个桶；

3. 最后，倒序遍历“桶”所使用的数组，按照从小到达的顺序拼接字符串，即可得到结果。

这个答案是最新一次做的。

```java

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyCount = new HashMap<>();
        for(int n : nums) {
            frequencyCount.put(n, frequencyCount.getOrDefault(n, 0) + 1);
        }

        ArrayList<Integer>[] buckets = new ArrayList[ nums.length + 1 ];
        for(int key : frequencyCount.keySet()) {
            int count =  frequencyCount.get(key);
            if( buckets[ count ] == null ) {
                buckets[ count ] = new ArrayList<Integer>();
            }
            buckets[ count ].add(key);
        }

        int[] ans = new int[k];
        int count = 0;

        for(int i = buckets.length - 1; i > 0; i--) {
            if( buckets[i] == null ) {
                continue;
            }
            ArrayList<Integer> list = buckets[i];
            for( int val  : list) {
                k -= 1;
                ans[count++] = val;
                if(k < 1) {
                    return ans;
                }
            }
        }
        return ans;
    }
}
```

下面这个是更新前的 bucket sort.

```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int i : nums){
            frequency.put(i, frequency.getOrDefault(i,0) + 1);
        }

        List<Integer>[] bucket = new LinkedList[nums.length + 1];

        for(int key : frequency.keySet()){
            int count = frequency.get(key);
            if(bucket[count] == null) {
                bucket[count] = new LinkedList<Integer>();
            }
            bucket[count].add(key);
        }

        List<Integer> topK = new ArrayList<>();
        for(int i = bucket.length - 1; i > 0; i--){
            if(bucket[i] == null) continue;
            if(topK.size() >= k) break;
            topK.addAll(bucket[i]);
        }
        // while(topK.size() > k){
        //     topK.remove(topK.size() - 1);
        // }
        return topK;
    }
}
```
