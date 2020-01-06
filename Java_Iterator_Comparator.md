# java Iterator Comparator

## Comparator<T>
1. 为什么重要？<br> 
   LeetCode 题目中经常需要你重载原来默认的Comparator

2. Comparator<T>是一个接口。<br>
   Comparator<T> 是 java.util. 下的一个interface. 
3. 实现一个自己的Comparator类只需要实现一个函数。<br>
   它只有两个method, 一个是 int compare(T o1, T o2), 一个是 boolean equals(Boject obj);<br>
    由于任何类都继承于 java.lang.Object, 在Object.java中就实现了equals(Boject obj). <br>
    **因此重写它只需要重写 compare(T o1, T o2) method 就可以了。**

4. 对于一个没有implements comparator的类，我们可以实现Comparator 类来新建一个比较器。<br>
   例1 自[LC1311](leetCode-1311-Get-Watched-Videos-by-Your-Friends.md)
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
    更好的办法就是自Java 8 之后可以用Lambda function直接新建一个比较器。
    而Java 8 之后 实现List接口的类都有 sort(Comparator<? super E> c) 方法直接传递进一个比较器
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



### LC 实战
1. 最常见的使用就是在heap中。
参见[Queue](DSAlgo_Queue.md)的PriorityQueue章节。
但是它的实现比较简单。
2. 其次就是在一些特殊的排序当中。



## Comparable
public class 如果实现了 Comparable, 那么一定要实现 compareTo
返回比较器。