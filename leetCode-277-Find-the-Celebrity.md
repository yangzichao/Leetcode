# 277J. Find the Celebrity

https://leetcode.com/problems/find-the-celebrity/
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

简而言之，只有一个名人。名人不知道任何人。任何人都知道名人。
## Method: Two Pass O(N)
原理是假设第一位是名人的标签。那么那名人和下一个去比较。
如果名人知道下一个人，说明第0位肯定不是名人，让下一个人做暂时名人。
如果名人不知道下一个人，说明下一个人肯定不是名人，继续比较下下个人。
这样每次比较都会减少一个候选者。
到最后会剩下唯一的一个候选者，为了确认，我们从头到尾过一遍看看他是不是
一个人也不认识，是不是所有人都认识他。当然还要注意排除他自己认识自己的情况。

```Java
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int cele = 0;
        for(int i = 1; i < n; i++ ){
            if(knows(cele,i)){
                cele = i;
            }
        }
        for(int i = 0; i < n; i++){
           if((!knows(i,cele)||knows(cele,i))&&(i!=cele)){
               return -1;
           }
        }
        return cele;
    }
}
```
