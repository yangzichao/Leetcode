# 118J. Pascal's Triangle

https://leetcode.com/problems/pascals-triangle/

## methods
主要为了学习一些Java 基础

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> myPascal = new ArrayList<List<Integer>>();
        if(numRows == 0){
            return myPascal;
        }
        myPascal.add(new ArrayList<Integer>());
        myPascal.get(0).add(1);
        int n = 1;
        int tempSum = 0;
        while( n < numRows ){
            myPascal.add(new ArrayList<Integer>());
            for(int i = 0; i < n + 1; i++){
                if(i == 0 || i == n ){
                    myPascal.get(n).add(1);
                }else{
                    tempSum = myPascal.get(n-1).get(i-1) + myPascal.get(n-1).get(i);
                    myPascal.get(n).add(tempSum);
                }
            }
            n++;
        }
        return myPascal;
    }
}
```
