# 460 LFU-Cache

difficulty: Hard

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Design and implement a data structure for <a href="https://en.wikipedia.org/wiki/Least_frequently_used" target="_blank">Least Frequently Used (LFU)</a> cache. It should support the following operations: <code>get</code> and <code>put</code>.</p>
<p><code>get(key)</code> - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.<br>
<code>put(key, value)</code> - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least <b>recently</b> used key would be evicted.</p>
<p>Note that the number of times an item is used is the number of calls to the&nbsp;<code>get</code>&nbsp;and&nbsp;<code>put</code>&nbsp;functions for that item since it was inserted. This number is set to zero when the item is removed.</p>
<p>&nbsp;</p>
<p><b>Follow up:</b><br>
Could you do both operations in <b>O(1)</b> time complexity?</p>
<p>&nbsp;</p>
<p><b>Example:</b></p>
<pre>LFUCache cache = new LFUCache( 2 /* capacity */ );
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
</pre>
<p>&nbsp;</p>
</div></section>
 
 ## Method One 
 
``` Java
class LFUCache {
    class Node{
        int key;
        int val;
        int count;
        Node prev;
        Node next;
        public Node(){};
        public Node(int key, int val ) {
            this.key = key;
            this.val = val;
            this.count = 1;
        }
    }
    
    class DLList{
        private int size;
        private Node head;
        private Node tail;
        public DLList(){
            this.head = new Node();
            this.tail = new Node();
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }
        
        public int size() {
            return this.size;
        }
        
        public void add(Node node) {
            Node next = head.next;
            head.next = node;
            next.prev = node;
            node.prev = head;
            node.next = next;
            this.size += 1;
        } 
        
        public Node remove( Node node ) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            node.next = null;
            node.prev = null;
            this.size -= 1;
            return node;
        }
        
        public Node getLast() {
            return tail.prev;
        }
    }
    
    private Map<Integer, Node> cache;
    private Map<Integer, DLList> frequencies;
    private int size;
    private int curMinFrequency;
    private int capacity;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.curMinFrequency = 0;
        this.cache = new HashMap<>();
        this.frequencies = new HashMap<>();
        frequencies.put(1, new DLList());
    }
    
    public int get(int key) {
        if( !cache.containsKey(key) ) {
            return -1;
        }
        update( cache.get(key) );
        return  cache.get(key).val;
    }
    
    public void put(int key, int value) {
        if(capacity < 1) {
            return;
        }
        if( cache.containsKey(key) ) {
            cache.get(key).val = value;
            update(cache.get(key));
            return;
        }
        if( cache.size() >= capacity ) {
            DLList curMinList = frequencies.get(curMinFrequency);
            Node curLastNode = curMinList.getLast();
            curMinList.remove(curLastNode);
            cache.remove( curLastNode.key );
            size -= 1;
        }
        
        size += 1;
        curMinFrequency = 1;
        
        Node node = new Node(key, value);
        cache.put(key, node);
        frequencies.get(1).add(node);
    }
    
    public void update( Node node ) {
        int oldCount =  node.count;
        int newCount = oldCount + 1;
        DLList oldList = frequencies.get( oldCount );
        oldList.remove(node);
        if( curMinFrequency == oldCount && oldList.size() == 0 ) {
            curMinFrequency++;
        } 
        frequencies.putIfAbsent( newCount, new DLList() );
        frequencies.get(newCount).add(node);
        node.count += 1;
    }
}

/\*\*

- Your LFUCache object will be instantiated and called as such:
- LFUCache obj = new LFUCache(capacity);
- int param_1 = obj.get(key);
- obj.put(key,value);
  \*/

```

```
