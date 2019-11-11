# 119J. Pascal's Triangle II

https://leetcode.com/problems/pascals-triangle-ii/description/

## method
学习一些基础的Java

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> myPascal = new ArrayList<Integer>();
        myPascal.add(1);        
        if( rowIndex == 0){
            return myPascal;
        }
        int n = 1;
        int tempSum = 0;
        while(n < rowIndex + 1){
            for(int i = n - 1; i > 0; i--){
                tempSum = myPascal.get(i) + myPascal.get(i-1);
                myPascal.set(i,tempSum);
            }
            myPascal.add(1);
            n++;
        }
        return myPascal;
    }
}
```
