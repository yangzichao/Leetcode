# 017J. Letter Combinations of a Phone Number
https://leetcode.com/problems/letter-combinations-of-a-phone-number/

## Method Best
这个题没啥意思啊。
```java
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<String>(); 
        if(digits==null||digits.length()==0) return ans;
        char[][] map = new char[10][];
        map[2]="abc".toCharArray();
        map[3]="def".toCharArray();
        map[4]="ghi".toCharArray();
        map[5]="jkl".toCharArray();
        map[6]="mno".toCharArray();
        map[7]="pqrs".toCharArray();
        map[8]="tuv".toCharArray();
        map[9]="wxyz".toCharArray();
        
        char[] input = digits.toCharArray();
        

        ans.add("");
        for(char c : input){
            ans = add(ans, map[ c - '0']);
        }
        return ans;
    }
    
    // 因为需要把旧的String更新一下，与其删掉旧的
    // 不如把旧的List of String 拿出来然后更新，并且加入到一个新的答案。返回给ans.
    public List<String> add(List<String> old, char[] set){
        List<String> update = new ArrayList<String>();
        for(String s : old){
            for(char c : set){
                update.add( s + c);
            }
        }
        return update;
    }
}
```

