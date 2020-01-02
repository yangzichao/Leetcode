# 位运算总结 Bit Manipulation

136 137

## Swap
<pre>
注意要避免 a == b 的swap，否则就坏了。
交换 a,b
a = a^b;
b = a^b; (a^b)^b = a
a = a^b; (a^b)^((a^b)^b) = (a^b)^a = b 
</pre>