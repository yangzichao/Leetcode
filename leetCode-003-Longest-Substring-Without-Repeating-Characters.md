# 003J. Longest Substring Without Repeating Characters

https://leetcode.com/problems/longest-substring-without-repeating-characters/

## Method: Sliding Window almost best

原理是用双指针加 HashSet 保存元素。i j 一慢一快。
j 指针快，用来检测指向的位置是否已经存在于 Set 之中。
如果没有的话就移动 j，保持 i 不变。如果 j 指向的元素
已经存在于 set 之中，那么就移出 i 指向的元素，i++，
继续下一次检测，直到 i 移除了和 j 指向的相同的元素。

改进方案是，用 HashMap 直接记录位置，可以较大的减少循环的次数。

```Java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0, j = 0;
        while(j < n){
            if( !set.contains(s.charAt(j)) ){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }else{
                set.remove(s.charAt(i++));
            }

        }
        return ans;

    }
}
```

用 for 的写法:

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int max = 0;
        int left = 0;
        for(char c : s.toCharArray()){
            if( window.size() == 0 || !window.contains(c)) {
                window.add(c);
                max = Math.max(max, window.size());
                continue;
            }
            while(window.contains(c)){
                window.remove(s.charAt(left));
                left++;
            }
            window.add(c);
            max = Math.max(max, window.size());
        }
        return max;
    }
}
```

发现上面 if 语句可以直接优化掉

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int max = 0;
        int left = 0;
        for(char c : s.toCharArray()){
            while(window.contains(c)){
                window.remove(s.charAt(left));
                left++;
            }
            window.add(c);
            max = Math.max(max, window.size());
        }
        return max;
    }
}
```
