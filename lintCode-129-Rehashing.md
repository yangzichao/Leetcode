# 129J ReHashing

https://www.lintcode.com/problem/rehashing/description

```java
/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
    public ListNode[] rehashing(ListNode[] hashTable) {
        if(hashTable.length <= 0){
            return null;
        }
        int oldM = hashTable.length; // old number of buckets.
        ListNode[] newHashTable = new ListNode[2*oldM];
        int newM = newHashTable.length;
        for(int i = 0; i < hashTable.length; i++){
            while(hashTable[i] != null){
                // int hash = newM;
                int newIndex = (hashTable[i].val%newM + newM)%newM;
                if(newHashTable[newIndex] == null){
                    newHashTable[newIndex] = new ListNode(hashTable[i].val);
                }else{
                    ListNode p = newHashTable[newIndex];
                    while(p.next!=null){
                        p = p.next;
                    }
                    p.next = new ListNode(hashTable[i].val);
                }
                hashTable[i] = hashTable[i].next;
            }
        }
        return newHashTable;
    }
};
```
