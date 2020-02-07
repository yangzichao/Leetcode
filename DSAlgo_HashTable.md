# HashSet/Map

待处理待一些
java hashmap 原理及实现
https://yikun.github.io/2015/04/01/Java-HashMap%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E5%8F%8A%E5%AE%9E%E7%8E%B0/

## 哈希第一步：一起哈希

Hash 是什么？

hash（散列、杂凑）函数，是将任意长度的数据映射到有限长度的域上。直观解释起来，就是对一串数据 m 进行杂糅，输出另一段固定长度的数据 h，作为这段数据的特征（指纹）。

Hash 就是把一些元素，试图尽量均匀地放在我们的连续内存地址桶里。到时我们直接去 HashCode 值找元素，就比较快。

hashCode 的存在主要是提高查找速度。hashcode 用来确定对象在散列中的地址。

如果对象 equals 相等，hashcode 一定相等。

如果 hashcode 相等，对象不一定相同，只能说明对象在同一个地址中。这就是碰撞。

## HashFunctions & HashCode

常用的哈希函数/散列函数有以下三种：

1. 直接取余
   Hash 主要使用的方法就是取余。即
   $f(x) =  x \text{ mod } M$, M 一般为一个不太接近$2^n$的一个质数。
2. 乘法取整
3. 平方取中

### 正整数

秦九韶算法/Horner 算法
其实就是计算多项式的值的方法。
比如一个链表 1 -> 2 -> 3 表示 123.
我们 0 \* 10 + 1 = 1， 1 \* 10 + 2 = 12， 12 \* 10 + 3 = 123;
这就是秦九韶算法，用来计算多项式子的值，很方便。
当我们遇到 N 进制的时候，自动的用这种算法就很方便。

### 字符串

字符串的 HashCode 是怎么计算的呢？
下面是 java.lang.StringLatin1 的实现.

```java
    public static int hashCode(byte[] value) {
        int h = 0;
        for (byte v : value) {
            h = 31 * h + (v & 0xff); // 0xff = 255，EASCII,扩展ASCII码
        }
        return h;
    }
```

seed 这里用的是 31. 即假设字符串是 31 进制。
选择 31 的理由是它是一个奇素数，使用素数并不比合数效果好很多，但是这是习惯。
31 有个特性，可以用移位和减法来代替乘法：

```java
31 * i == ( i << 5 ) - i;
```

我们选择 31 作为进制，可以预见，很快整型就会遇到**溢出**的问题。
Java 处理溢出的方式就是让它顺其自然。整型会自动取最后 32 位二进制。
所以 Java 的处理方式，会返回一个 32 位的 hashcode。

实际我们做题的时候,我们的 buckets 没有 32 位，我们就需要手动处理溢出。
这时我们就需要模运算。比如我们有 M 个 buckets。

```java
 h = ( 31 * h + (v & 0xff) ) % M;
```

这就是我们在 Java 中的操作。
实际上我们做题的时候，会选用 256 进制，即种子为 256。M 取的是我们需要的数量。

#### Rabin-Karp 算法

Rabin-Karp 方法是一个字符串查找算法，还是蛮常用的。其他的查找算法和它经常可以用来
处理类似的问题。

题目：
直接应用
[LinCode594](https://www.lintcode.com/problem/strstr-ii/description)
[LeetCode 028](https://leetcode.com/problems/implement-strstr/)
是解答方法之一：
[LeetCode 214](https://leetcode.com/problems/shortest-palindrome/)
[LeetCode 686](https://leetcode.com/problems/repeated-string-match/)

参考：
https://www.jianshu.com/p/2303da7ba4d6
https://www.youtube.com/watch?v=G-YIYLwI95k&t=18s

Rabin-Larp 的核心思想是，计算子字符串的哈希值进行比较。然后滑动窗口。

**首先** 计算哈希值。
假设我们 256 进制，桶的数量有 Q 个。哈希值的计算如下:
这里我们运用的自然是 秦九韶算法/Hooner 算法。

```java
int R = 256;
private static long hash(String key, int M){
    long h = 0; // 为什么是 long 呢？奥妙在下面解释。
    for(int i = 0; i < M; i++){
        h = (h * R + key.charAt(i)) % Q; // 因为 h*R的时候，可能直接溢出，所以用long保险一点。
    }
    return h;
}
```

这段代码什么意思呢？
在这之前先了解下一个取余的基本性质：如果在每次算术操作后都将结果除以 Q 取余，
这等价于在完成了所有算术操作后将最后结果对 Q 取余。这就是同余定理，
是[同余的基本性质。](https://en.wikipedia.org/wiki/Modular_arithmetic)

举个简单的例子：

```
(A + B) % Q = (A % Q + B % Q) % Q
(A * B) % Q = (A % Q * B % Q) % Q
```

这个非常好理解，因为一个数被模一次，就一定小于 Q 了，后面连着模再多次效果都是一样的。
但是注意上面的式子不可以用分配律。上面的式子已经是最简洁的形式了。想想为什么。

可以参考[这个](http://www.matrix67.com/blog/archives/236)
当然可以参考很多了。

所以上面的计算就相当于是，**计算 字符串的 R 进制值然后模 Q。**

然后我们开始滑动窗口比较。
我们知道滑动窗口需要加一位新的，减去一位旧的。
如果是纯数字，我们当然知道怎么做。比如 12345 中的 234 滑动到 345。
我们 （234 - 2\*100）\* 10 + 5 就可以了。
而 R 进制里，R 代替了 10。问题就是计算这个 100, 即 10^(3-1).

假设我们的滑动之前的哈希值是 oldHash, 那么我们新的哈希值 newHash 应当

newHash = ( oldHash - R^(L - 1)%Q )\*R + v;

计算这个 R^N%Q, 困难在于，我们这里的 R^N 是可能溢出的。这里又要用到前面的同余定理了。
所以有我们如下代码

```java
long RK = 0;
for(int i = 0; i < L - 1; i++){
    RK = (RK*R) % Q;
}
```

滑窗的部分 核心是

```java
newHash = (newHash - RK * text.charAt(i - L) + Q ) * R + text.charAt(i) ) % Q;
// 第一个 + Q 是为了防止出现负的哈希值。反正 Q % Q是 0.
```

Rabin-Karp 最后就是滑窗，如果哈希值匹配，那么再逐个检查一下，以防碰撞。
