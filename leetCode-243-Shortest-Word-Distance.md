# 243J. Shortest Word Distance
https://leetcode.com/problems/shortest-word-distance/


## Method 1 and Best: one pass
方法是不断更新最小距离。
```Java
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int m = -1, n = -1;
        int minDistance = words.length;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                m = i;
            }
            if(words[i].equals(word2)){
                n = i;
            }
            if(m!=-1 &&n!=-1){
                 minDistance = Math.min(minDistance, Math.abs(m-n));
            }

        }
        return minDistance;
    }
}
```
