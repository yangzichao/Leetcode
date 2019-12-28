# 位运算总结 Bit Manipulation

136 137

## Swap
交换 a,b
a = a^b;
b = a^b; (a^b)^b = a
a = a^b; (a^b)^((a^b)^b) = (a^b)^a = b 