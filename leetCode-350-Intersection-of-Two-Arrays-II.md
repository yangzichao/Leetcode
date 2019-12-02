# 350J. Intersection of Two Arrays II
https://leetcode.com/problems/intersection-of-two-arrays-ii/

此题的双指针方法还是需要看一看的
## Method HashMap
<pre>
思路是，先用hashmap存下随便一个数组里，元素出现的次数。
然后寻找的时候，每在hashmap找到一个，就让他的值减一。
储存返回结果就用原数组就好了。
直接使用 Arrays.copyOfRange()方法。
</pre>
```Java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> myMap = new HashMap<>();
        for(int n : nums1){
            myMap.put(n,myMap.getOrDefault(n,0) + 1);
        }
        int id = 0;
        for(int n : nums2){
            if(myMap.getOrDefault(n,0) > 0){
                nums2[id++] = n;
                myMap.put(n,myMap.get(n) - 1);
            }
        }
        return Arrays.copyOfRange(nums2,0,id);
    }
}
```
## Method2 Sort and Two pointers
<pre>
先排序，然后再挨个比较。
</pre>
```Java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, count = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] < nums2[j]){
                i++;
            }else if(nums1[i] > nums2[j]){
                j++;
            }else{
                ans[count] = nums1[i];
                i++;
                j++;
                count++;
            }
        }
        return Arrays.copyOf(ans,count);
    }
}
```
