字典序（dictionary order），又称 字母序（alphabetical order），原意是表示英文单词在字典中的先后顺序，在计算机领域中扩展成两个任意字符串的大小关系。

英文中的 字母表（Alphabet） 按照如下的顺序排列：

```
1
ABCDEFG HIJKLMN OPQRST UVWXYZ
2
abcdefg hijklmn opqrst uvwxyz
```

```
as
aster
astrolabe
astronomy
astrophysics
at
ataman
attack
baa
```

在 Java 语言 中：

```java
System.out.println("ah1x".compareTo("ahb"));
```

会输出 -49，这个数是两个字符串第一个不一样的位置的两个字符的 ASCII 值之差，如果小于零则说明第一个字符串小于第二个字符串。
