# 381J. Insert Delete GetRandom O(1) - Duplicates allowed

https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

核心思想是，ArrayList + HashMap.  
那么如何避免 ArrayList remove 方法的 O(N) 呢？
我们利用它的 get() 的 O(1), 把它和最后一个元素 swap 之后
再删除，这样就有了 O(1) 的复杂度。

```java
class RandomizedCollection {
    private Map<Integer, Set<Integer>> dict;
    private ArrayList<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.dict = new HashMap<>();
        this.list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {

        dict.putIfAbsent(val, new HashSet<Integer>());
        int size = list.size();
        dict.get(val).add(size);
        list.add(size, val);
        return dict.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if( !dict.containsKey(val) || dict.get(val).size() == 0) {
            return false;
        }
        int index = dict.get(val).iterator().next();
        dict.get(val).remove(index);

        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        list.set(index, lastValue);
        dict.get(lastValue).add(index);

        list.remove(lastIndex);
        dict.get(lastValue).remove(lastIndex);

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get( (int) Math.floor( Math.random() * list.size() ) );
    }
}
```

```java
class RandomizedCollection {
    private ArrayList<Integer> list;
    private HashMap<Integer,Set<Integer>> idxMap;
    private java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        this.list = new ArrayList<>();
        this.idxMap = new HashMap<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(!idxMap.containsKey(val)){
            idxMap.put(val,new HashSet<Integer>());
        }
        idxMap.get(val).add(list.size());
        list.add(val);
        return  idxMap.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!idxMap.containsKey(val) || idxMap.get(val).size() == 0) {
            return false;
        }
        //我们一次只拿掉一个，即使有重复的也只拿掉任意一个。
        //从它的 index 集合里面取出下一个 并且从集合中删掉。
        int removedIndex = idxMap.get(val).iterator().next();
        idxMap.get(val).remove(removedIndex);
        //处理List的操作，方法是，把要删的元素和最后一个交换。然后删除最后。
        //这样不会影响其他的顺序
        int lastVal = list.get(list.size() - 1);
        list.set(removedIndex, lastVal); // 这是ArrayList 更新值的方法
        //再来更新一下map.
        idxMap.get(lastVal).add(removedIndex);
        idxMap.get(lastVal).remove(list.size() - 1);

        list.remove(list.size() - 1); // 把最后一位删掉。
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
```
