# 274J  H-Index.
https://leetcode.com/problems/h-index/

其实是一个排序题

## Method1: 排序再找H因子


```java
class Solution {
    public int hIndex(int[] citations) {
        int h = 0;
        Arrays.sort(citations);
        for(int i = citations.length - 1; i > -1; i--){
            if(citations[i] >= citations.length - i ){
                h++;
            }
        }
        return h;
    }
}
```

## Method2: 时间方法更佳 Counting Sort / Bucket Sort
<pre>
数组编号
0 1 2 3 4
引用数
3 0 6 1 5
counting sort, 新建一个比原数组大一的数组，计算值为 x < n 的个数
0 1 2 3 4 5
大于 n 的都存在 n + 1的格子里
第一个 3
0 1 2 3 4 5
0 0 0 1 0 0
第二个 0
0 1 2 3 4 5
1 0 0 1 0 0
第三个 6
0 1 2 3 4 5
1 0 0 1 0 1
第四个 1
0 1 2 3 4 5
1 1 0 1 0 1
第五个 5
0 1 2 3 4 5
1 1 0 1 0 2
此时这个新数组已经变相排好序了
从后往前加总数，当sum >= i 的时候，i 就是了h因子了
</pre>

```Java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] countSort = new int[n+1];
        for(int c : citations){
            countSort[Math.min(n,c)]++;
        }
        int sum = 0;
        for(int h = n; h > -1;h--){
            sum+=countSort[h];
            if(sum >= h){
                return h;
            }
        }
        return 0;
    }
}
```
