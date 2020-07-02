# java Iterator Comparator

## Comparator<T>

1. 为什么重要？  
   LeetCode 题目中经常需要你重载原来默认的 Comparator

2. Comparator<T>是一个接口。  
   Comparator<T> 是 java.util. 下的一个 interface.

3. 实现一个自己的 Comparator 类只需要实现一个函数。  
   它只有两个 method,
   一个是 int compare(T o1, T o2),
   一个是 boolean equals(Boject obj);  
   由于任何类都继承于 java.lang.Object, 在 Object.java 中就实现了 equals(Boject obj).  
   **因此重写 Comparator 只需要重写 compare(T o1, T o2) method 就可以了。**

4. 对于一个没有 implements comparator 的类，我们可以实现 Comparator 类来新建一个比较器。  
   例 1 自 [LC1311](leetCode-1311-Get-Watched-Videos-by-Your-Friends.md)

   ```java
   ans.sort(new pseudoComparator());

   private class pseudoComparator implements Comparator<String>{
       public int compare(String s1, String s2){
           if(map.get(s1) != map.get(s2)){
               return map.get(s1) - map.get(s2);
           }
           return s1.compareTo(s2);
       }
   }
   ```

   更好的办法就是自 Java 8 之后可以用 Lambda function 直接新建一个比较器。
   而 Java 8 之后 实现 List 接口的类都有 sort(Comparator<? super E> c) 方法直接传递进一个比较器

   ```java
   ans.sort(
       (a,b) -> {
           if(map.get(a) != map.get(b)){
               return map.get(a) - map.get(b);
           }
           return a.compareTo(b);
       }
   );
   ```

compareTo(): 字符串常用。按
[字典排序](others/Alphbetical-Order-Dictionary-Order.md)，
如果 a == b, 返回 0.
如果 a < b, 即 a 排在 b 之前，返回 负数  
如果 a > b, 即 a 排在 b 之后，返回 正数

在 Java 语言 中：

```java
System.out.println("ah1x".compareTo("ahb"));
```

会输出 -49，这个数是两个字符串第一个不一样的位置的两个字符的 ASCII 值之差，如果小于零则说明第一个字符串小于第二个字符串。

数组 Comparator

需要 **Custom Sort** 的时候。
可以直接调用 Arrays.sort(数组, comparator);
例如 [937](leetCode-937-Reorder-Data-in-Log-Files.md);

```java
    PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new pseudoComparator());
    private class pseudoComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return a[0] - b[0];
        }
    }
```

用 Override 的方法

```java
    PriorityQueue<int[]> q = new PriorityQueue<int[]>(k + 1, new Comparator<int[]>(){
        @Override
        public int compare(int[] k, int[] l){
            return l[0] * l[0] + l[1] * l[1] - k[0] * k[0] - k[1] * k[1];
        }
    });
```

### LC 实战

1. 最常见的使用就是在 heap 中。
   参见[Queue](DSAlgo_Queue.md)的 PriorityQueue 章节。
   但是它的实现比较简单。
2. 其次就是在一些特殊的排序当中。
   LC179

## Comparable

public class 如果实现了 Comparable, 那么一定要实现 compareTo
返回比较器。
