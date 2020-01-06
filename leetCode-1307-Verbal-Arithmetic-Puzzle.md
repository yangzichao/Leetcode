# 1307J. Verbal Arithmetic Puzzle
https://leetcode.com/problems/verbal-arithmetic-puzzle/

## Method BackTracking 
这是一个相对水的[BackTracking](DSAlgo_Backtracking.md)的题，相关的题目有
主要的问题是容易超时。由于Map的Key可以明显的map到整型上，所以都用int[] 来代替map就很快了。
注意的问题是，一定要注意防止首字母出现0的可能。
```java
class Solution {
    private String[] words;
    private String result;
    private int[] charSet;
    private int[] initialSet;
    private int[] dict;
    private int[] ten;
    
    public boolean isSolvable(String[] words, String result) {
        this.words = words;
        this.result = result;
        this.charSet = new int[26];
        this.initialSet = new int[26];
        this.dict = new int[26];
        this.ten = new int[10];
        
        addToSet(result);
        for(int i = 0; i < this.words.length; i++){
            addToSet(this.words[i]);
        }
        return scanner(0);
    }
    
    private boolean scanner(int index){
        if(index == 26) {           
            return valid();
        }
        if(charSet[index] == 0){
            return scanner(index + 1);
        }        
        for(int i = 0; i < 10 ; i++){
            if(i == 0 && initialSet[index]!= 0){
                continue;
            }
            if(ten[i] > 0){
                continue;
            }
            dict[index] = i;
            ten[i] = 1;
            if( scanner(index + 1) ){
                return true;
            }
            dict[index] = 0;
            ten[i] = 0;     
        }
        return false;
    }
    
    private int wordToInt(String word){
        int ans = 0;
        for(int i = 0; i < word.length(); i++){
            // char c = word.charAt(i);
            ans = ans*10 + dict[  word.charAt(i) - 'A'];
        }
        return ans;
    }
    
    private void addToSet(String word){
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(i == 0){
                initialSet[c - 'A'] = 1;
            }
            charSet[ c - 'A' ] = 1;
        }
    }
    
    private boolean valid(){
        int ans = 0;
        for(int i = 0; i < this.words.length; i++){
            ans += wordToInt(this.words[i]);
        }
        int result = wordToInt(this.result);
        return ans == result;
    }
}
```