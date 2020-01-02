# 412J. Fizz Buzz
https://leetcode.com/problems/fizz-buzz/

## Naive

```java
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if( i%3 == 0 && i%5!=0){
                ans.add("Fizz");
            }else if( i% 5 == 0 && i%3 != 0){
                ans.add("Buzz");
            }else if(i%5 == 0 && i%3 == 0){
                ans.add("FizzBuzz");
            }else{
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }
}
```
