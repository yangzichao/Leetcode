# 003J. Longest Substring Without Repeating Characters
https://leetcode.com/problems/longest-substring-without-repeating-characters/

## Method: Sliding Window almost best

原理是用双指针加HashSet保存元素。i j 一慢一快。
j 指针快，用来检测指向的位置是否已经存在于Set之中。
如果没有的话就移动 j，保持i不变。如果j 指向的元素
已经存在于set 之中，那么就移出i指向的元素，i++，
继续下一次检测，直到i移除了和j指向的相同的元素。

改进方案是，用HashMap可以较大的减少循环的次数。
```Java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0, j = 0;
        while(i < n && j < n){

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
