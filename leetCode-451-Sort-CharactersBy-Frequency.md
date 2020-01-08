# 451J. Sort Characters By Frequency
https://leetcode.com/problems/sort-characters-by-frequency/

## Method Best 桶排序
用桶排序还挺简单的。直接线性时间。
```java
class Solution {
    public String frequencySort(String s) {
        int[] map = new int[128];
        for(char c : s.toCharArray()){
            map[(int) c] += 1;
           
        }
        List<Character>[] bucket = new ArrayList[s.length() + 1];
        for(int ckey = 0;  ckey < 128; ckey++){
            if(bucket[map[ckey]] == null){
                bucket[map[ckey]] = new ArrayList<>();     
            }
            bucket[map[ckey]].add( (char) ckey);
        }
        StringBuilder ans = new StringBuilder();
        for(int i = bucket.length - 1; i > 0; i--){
            if(bucket[i]!=null){
                for(char c : bucket[i]){
                    int size = map[c];
                    while(size > 0){
                        ans.append(String.valueOf(c));
                        size--;
                    }
                }
            }
        }
        return ans.toString();
    }
}
```

## Method 2 PQ
上面对map的排序还可以用优先队列。还行吧。