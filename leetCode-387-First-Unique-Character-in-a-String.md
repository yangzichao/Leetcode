# 387J. First Unique Character in a String

# Method Best
唯一的办法就是循环两遍 第一遍加入HashMap中，
第二遍找第一个值为1的。
```Java
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character,Integer> myMap = new HashMap<Character, Integer>();
        int temp = 0;
        for(int i = 0; i < s.length(); i++){
            if(myMap.get(s.charAt(i))!=null){
                temp = myMap.get(s.charAt(i)) + 1;
                myMap.put(s.charAt(i),temp);
            }else{
                myMap.put(s.charAt(i),1);
            }
        }

        for(int i = 0; i < s.length(); i++){
            if(myMap.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }
}

```

改进方法:
用一个26字母数组来充当HashMap，实际是O(1) Space
```Java
public class Solution {
    public int firstUniqChar(String s) {
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;
        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}
```
