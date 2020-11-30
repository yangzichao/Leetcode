# 706 Design-HashMap 
 
difficulty: Easy 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Design a HashMap&nbsp;without using any built-in hash table libraries.</p>
<p>To be specific, your design should include these functions:</p>
<ul>
	<li><code>put(key, value)</code> :&nbsp;Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.</li>
	<li><code>get(key)</code>: Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.</li>
	<li><code>remove(key)</code> :&nbsp;Remove the mapping for the value key if this map contains the mapping for the key.</li>
</ul>
<p><br>
<strong>Example:</strong></p>
<pre>MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
hashMap.put(2, 2); &nbsp; &nbsp; &nbsp; &nbsp; 
hashMap.get(1); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns 1
hashMap.get(3); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns -1 (not found)
hashMap.put(2, 1); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// update the existing value
hashMap.get(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns 1 
hashMap.remove(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// remove the mapping for 2
hashMap.get(2); &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;// returns -1 (not found) 
</pre>
<p><br>
<strong>Note:</strong></p>
<ul>
	<li>All keys and values will be in the range of <code>[0, 1000000]</code>.</li>
	<li>The number of operations will be in the range of&nbsp;<code>[1, 10000]</code>.</li>
	<li>Please do not use the built-in HashMap library.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class MyHashMap {
    private class Node{
        int key;
        int val;
        Node next;
        public Node(){};
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    // 这个加个 dummyHead 返回 prev 节点确实是最佳设计。
    // 因为 prev 可以进行灵活的各种操作比如 append, delete
    
    private Node findNode(Node root, int key ){
        Node prev = root;
        Node p = root.next;
        while( p != null && p.key != key ){
            prev = p;
            p = p.next;
        }
        return prev;
    }
        
    private Node[] buckets; 
    private int size;
    private int capacity = 9973;
    // 9973 is the largest prime smaller than 10000, just a random pick here
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        this.buckets = new Node[capacity];
        this.size = 0;
    }
    
    // 这个 Integer.hashCode(key) 可以解释一下, 事实上 java 就是返回的 int 的值. 直接 key%capacity 一样。
    private int index(int key){
        return key % capacity ;
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        if( buckets[ index(key) ] == null ){
            buckets[ index(key) ] = new Node(-1, -1); // dummyHead;
        }
        Node prev = findNode( buckets[ index(key) ], key );
        if( prev.next != null ){
            prev.next.val = value;
            return;
        }
        prev.next = new Node(key,value);
        size += 1;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if( buckets[ index(key) ] == null ){
            return -1;
        }
        
        Node prev = findNode( buckets[ index(key) ] , key);
        
        return prev.next == null ? -1 : prev.next.val;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        if( buckets[ index(key) ] == null ){
           return;
        }
        Node prev = findNode( buckets[ index(key) ] , key);
        if( prev.next == null ){
            return;
        }
        prev.next = prev.next.next;
        size -= 1;
    }
}

/**
For simplicity, are the keys integers only?
For collision resolution, can we use chaining?
Do we have to worry about load factors?
Can we assume inputs are valid or do we have to validate them?
*/
/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
```