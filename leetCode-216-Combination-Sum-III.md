# 216J. Combination Sum III
https://leetcode.com/problems/combination-sum-iii/


## Method Trivial:
的确不值一提。
```java
class Solution {
    private List<List<Integer>> ans;
    private List<Integer> temp;
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.ans = new LinkedList<>();
        this.temp = new LinkedList<>();
        backtracker(k, n, 1);
        return ans;
    }
    
    private void backtracker(int k, int target, int index){
        if(target < 0) return;
        if(k == 0){
            if(target == 0){
                ans.add(new LinkedList<>(temp));
            }
            return;
        }
        for(int i = index; i < 10; i++){
            temp.add(i);
            backtracker(k - 1, target - i, i + 1);
            temp.remove(temp.size() - 1);
        }
    }  
}
```
