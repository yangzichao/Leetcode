# 380 Insert-Delete-GetRandom-O(1)

difficulty: Medium

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Design a data structure that supports all following operations in <i>average</i> <b>O(1)</b> time.</p>
<p>&nbsp;</p>
<ol>
	<li><code>insert(val)</code>: Inserts an item val to the set if not already present.</li>
	<li><code>remove(val)</code>: Removes an item val from the set if present.</li>
	<li><code>getRandom</code>: Returns a random element from current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the <b>same probability</b> of being returned.</li>
</ol>
<p>&nbsp;</p>
<p><b>Example:</b></p>
<pre>// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();
// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);
// Returns false as 2 does not exist in the set.
randomSet.remove(2);
// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);
// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();
// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);
// 2 was already in the set, so return false.
randomSet.insert(2);
// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class RandomizedSet {
    private Map<Integer, Integer> cache;
    private ArrayList<Integer> array;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.cache = new HashMap<Integer, Integer>();
        this.array = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(cache.containsKey(val)) return false;
        int size = array.size();
        cache.put(val, size);
        array.add(size, val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!cache.containsKey(val)) return false;
        int last = array.get(array.size() - 1);
        int index = cache.get(val);
        array.set(index, last);
        cache.put(last, index);
        array.remove(array.size() - 1);
        cache.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = (int) Math.floor(Math.random() * array.size() );
        return array.get(index);
    }
}
​
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
​
```
